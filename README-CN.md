# 轻安卓 LightAndroid [English ReadMe](README.md)
[![交流区](http://img.shields.io/badge/chat-online-brightgreen.svg)](https://gitter.im/LightAndroid/Lobby)

轻安卓是用来帮助安卓程序员开发轻快应用的库

目前，它支持以下特性.
1. [LeakCanary](https://github.com/square/leakcanary) 为安卓和Java提供内存泄露检测的库.
2. [Glide](https://github.com/bumptech/glide) Glide 是一个为安卓平台提供的快速、高效、开源的媒体管理和图片加载框架,功能包括媒体解码,内存/硬盘缓存和资源池,用户交互简单方便.
3. [logback](https://github.com/tony19/logback-android) 安卓平台上 Java实现的可靠，通用，快速，灵活 的日志框架
4. [RxPermissions](https://github.com/tbruyelle/RxPermissions) 用RxJava的方式使用安卓M版本的权限模型.
    -[LightPermissions](https://github.com/YunlongYang/LightAndroid/blob/master/library/src/main/java/online/heyworld/lightandroid/feature/LightPermissions.java) LightPermissions 是 LightAndroid 对 RxPermissions的增强类.

## 怎么使用?
第一步, 把GitPack.IO添加你的根工程的repositories列表中:
<pre>
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
</pre>
第二步, 添加对LightAndroid的依赖
<pre>
dependencies {
    compile 'com.github.YunlongYang:LightAndroid:$LightAndroidVersion'
}
</pre>

第三步,　在应用中启用LightAndroid
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


## 注意事项
- 为了使用LeakCanary, 你需要把以下代码放入你的依赖中. 因为它在调试模式和发行模式下依赖的库是不同的.你可以在 [gradle.properties](gradle.properties) 找到 $LeakCanaryVersion .
<pre>
      debugImplementation "com.squareup.leakcanary:leakcanary-android:$LeakCanaryVersion"
      releaseImplementation "com.squareup.leakcanary:leakcanary-android-no-op:$LeakCanaryVersion"
</pre>


如果你有其他好的第三方库推荐或者在使用中出现什么问题，欢迎提出Issues、发送邮件到我的邮箱 yunlong17568@qq.com，也可以[加入交流区](https://gitter.im/LightAndroid/Lobby)参与讨论.