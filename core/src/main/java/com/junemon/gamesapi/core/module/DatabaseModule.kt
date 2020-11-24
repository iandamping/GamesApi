package com.junemon.gamesapi.core.module

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.annotation.RequiresApi
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.junemon.gamesapi.core.data.datasource.cache.preference.PreferenceHelper
import com.junemon.gamesapi.core.data.datasource.cache.preference.PreferenceHelperImpl
import com.junemon.gamesapi.core.data.datasource.cache.preference.value.ProvideValue
import com.junemon.gamesapi.core.data.datasource.cache.room.GameDatabase
import com.securepreferences.SecurePreferences
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import timber.log.Timber

/**
 * Created by Ian Damping on 23,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
private const val prefHelperInit = "Init preference"

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), GameDatabase::class.java, "gaming_database.db")
            .fallbackToDestructiveMigration()
            .openHelperFactory(provideSupportFactoryDatabase(get()))
            .build()
    }
    single { get<GameDatabase>().gameDao() }
    single { get<GameDatabase>().gameFavoriteDao() }
}

private fun provideSupportFactoryDatabase(provideValue: ProvideValue): SupportFactory {
    val passphrase: ByteArray = SQLiteDatabase.getBytes(provideValue.provideRoomPassPhraseValue().toCharArray())
    return SupportFactory(passphrase)
}


val sharedPreferencesModule = module {
    single { provideSharedPreferences(androidContext()) }
    single<PreferenceHelper> { PreferenceHelperImpl(get()) }
}

private fun provideSharedPreferences(context: Context): SharedPreferences {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        EncryptedSharedPreferences.create(
            context,
            prefHelperInit,
            getMasterKey(context),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    } else {
        SecurePreferences(context)
    }
}

@RequiresApi(Build.VERSION_CODES.M)
private fun getMasterKey(context: Context): MasterKey {
    val spec = KeyGenParameterSpec.Builder(
        MasterKey.DEFAULT_MASTER_KEY_ALIAS,
        KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
    )
        .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
        .setKeySize(256)
        .build()

    return MasterKey.Builder(context)
        .setKeyGenParameterSpec(spec)
        .build()
}

