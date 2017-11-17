# LightAndroid [简体中文说明](README-CN.md)
[![Gitter Chat](http://img.shields.io/badge/chat-online-brightgreen.svg)](https://gitter.im/LightAndroid/Lobby)

LightAndroid Library is used to help android programmer make a light app.

## Now, it contains the features etc.
1. [LeakCanary](https://github.com/square/leakcanary) A memory leak detection library for Android and Java.
2. [Glide](https://github.com/bumptech/glide) Glide is a fast and efficient open source media management and image loading framework for Android that wraps media decoding, memory and disk caching, and resource pooling into a simple and easy to use interface.

## How To Use?
Step 1. Add it in your root build.gradle at the end of repositories:
<pre>
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
</pre>
Step 2. Add the dependency
<pre>
dependencies {
    compile 'com.github.YunlongYang:LightAndroid:$LightAndroidVersion'
}
</pre>
Step 3.　Start LightAndroid in your application.
<pre>
public class MainApplication extends Application {
    LightAndroidApplicationLike lightAndroidApplicationLike;
    @Override
    public void onCreate() {
        super.onCreate();
        lightAndroidApplicationLike = new LightAndroidApplicationLike();
        lightAndroidApplicationLike.onCreate(this);
    }
}
</pre>

## Attentions
- To Use LeakCanary, you should add this code to your dependencies. Because LeakCanary will use different library in debug and release.You can find $LeakCanaryVersion in [gradle.properties](gradle.properties).
<pre>
      debugImplementation "com.squareup.leakcanary:leakcanary-android:$LeakCanaryVersion"
      releaseImplementation "com.squareup.leakcanary:leakcanary-android-no-op:$LeakCanaryVersion"
</pre>


If you have some good third party library to recommend or got problems when using LightAndroid, welcome to submit issues, send emails to me at yunlong17568@qq.com, you can also [join the chat room](https://gitter.im/LightAndroid/Lobby) to discuss.