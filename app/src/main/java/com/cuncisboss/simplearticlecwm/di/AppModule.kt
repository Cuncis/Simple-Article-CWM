package com.cuncisboss.simplearticlecwm.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.cuncisboss.simplearticlecwm.model.User
import com.cuncisboss.simplearticlecwm.util.Constants.ARTICLE_PREF_NAME
import com.cuncisboss.simplearticlecwm.util.Constants.EMAIL_PREF
import com.cuncisboss.simplearticlecwm.util.Constants.USERNAME_PREF
import com.cuncisboss.simplearticlecwm.util.Constants.PASS_PREF
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module


val prefModule = module {
    single { provideArticlePreferences(androidApplication()) }
    single(named("user")) { provideUser(get()) }
}

private fun provideArticlePreferences(app: Application): SharedPreferences =
    app.getSharedPreferences(ARTICLE_PREF_NAME, MODE_PRIVATE)

private fun provideUser(sharePref: SharedPreferences) =
    User(
        sharePref.getString(USERNAME_PREF, "").toString(),
        sharePref.getString(EMAIL_PREF, "").toString(),
        sharePref.getString(PASS_PREF, "").toString()
    )