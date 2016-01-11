### 前言
该项目是从[xUtils3](https://github.com/wyouflf/xUtils3)中剥离出来的. [xUtils3](https://github.com/wyouflf/xUtils3)项目中主要有3个部分组成,分别是网络组件,数据库组件和控件注入组件. 但由于网络组件可以由google自家的Volley框架来替代,数据库组件也有很多更好的的解决方案, 如[Sugar](https://github.com/satyan/sugar),[LitePal](https://github.com/LitePalFramework/LitePal)等.因此,本项目将控件注入组件从原项目中剥离出来,形成单独的项目,并上传到jCenter,方便开发者引用.本项目会定期检查xUtils的更新,并将相关更新进行同步.在此,感谢xUtils3的开发人员.本项目所有权利归xUtils开发人员所有.如有冒犯,可联系本人进行删除,谢谢!

### ViewInjector简介

ViewInjector是从[xUtils3](https://github.com/wyouflf/xUtils3)项目中剥离出来的一个进行控件依赖注入的微型框架.使用此项目,可以简化控件的声明,引用及控件事件的回调等操作.

#### 在Android项目中使用ViewInjector

#### 1 导入library
该项目已发布到jcenter上,可直接在gradle工程中进行引用.引用代码如下:

`compile 'com.frozy:view-injector:0.5.1'`

#### 2 初始化配置
与原项目类似,需要在项目的application中做如下初始化工作.假设项目的Application为BaseApplication.java,则配置如下:

BaseApplication.java

```java
@Override
public void onCreate() {
    super.onCreate();
    ...
    x.Ext.init(this);
    // 是否输出debug日志
    x.Ext.setDebug(BuildConfig.DEBUG);
    ...
}
```

#### 3 在activity中进行依赖注入

在activity中进行控件的依赖注入,可以设置activity的content view, 声明界面控件,进行控件回调等.

Now, show you the code ~

MainActivity.java

```java
@ContentView(R.layout.activity_main)
public class MainActivity extends Activity {
    
    @ViewInject(R.id.bt_signin) 
    Button mBtSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        ...
    }

    @Event(value = R.id.bt_signin, type = View.OnClickListener.class)
    private void onSigninButnClick(View view)
    {
        ...
    }
}
```

以上代码片段中简要介绍了ViewInjector的一般用法.

* activity的onCreate函数中将该activity进行注入:`x.view().inject(this)`
* `@ContentView(R.layout.activity_main)`设置该activity的content view.
* `@ViewInject(R.id.bt_signin) Button mBtSignin`声明了控件并自动映射view id.
* `@Event(value = R.id.bt_signin, type = View.OnClickListener.class)`进行事件分配与回调.
    * value为控件id, type为回调函数类型,默认为控件onClick事件的回调函数.
    * 此处函数声明必须为private, 函数参数形式必须与实际的回调函数的参数形式一致.
    * 方法以Click或Event结尾, 方便配置混淆编译参数.

#### 4 混淆配置
在混淆文件中,加入如下代码:

```
-keepattributes *Annotation*
-keepclassmembers class * {
    void *(android.view.View);
    *** *Click(...);
    *** *Event(...);
}
```
