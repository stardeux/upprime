package com.stardeux.upprime.tools.flipper

import android.content.Context
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.core.FlipperClient
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import org.koin.dsl.module

val flipperModule = module {
    factory {
        provideFlipperClient(
            get(),
            get()
        )
    }
    single { provideNetworkFlipperPlugin() }    //Single because provided twice : provideFlipperClient and provideOkHttp
    single {
        provideFlipperOkhttpInterceptor(
            get()
        )
    }

    factory { provideFlipperClient(get()) }
}

fun provideFlipperClient(context: Context): FlipperClient {
    return AndroidFlipperClient.getInstance(context)
}


fun provideNetworkFlipperPlugin(): NetworkFlipperPlugin {
    return NetworkFlipperPlugin()
}

fun provideFlipperClient(
    context: Context,
    networkFlipperPlugin: NetworkFlipperPlugin
): FlipperClient {
    return AndroidFlipperClient.getInstance(context).apply {
        addPlugin(networkFlipperPlugin)
    }
}

fun provideFlipperOkhttpInterceptor(networkFlipperPlugin: NetworkFlipperPlugin): FlipperOkhttpInterceptor {
    return FlipperOkhttpInterceptor(networkFlipperPlugin)
}