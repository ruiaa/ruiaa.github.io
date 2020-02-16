##	导入移动广告 SDK
项目级 build.gradle
allprojects {
    repositories {
        google()
        jcenter()
    }
}
应用级 build.gradle
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:26.1.0'
    implementation 'com.google.android.gms:play-services-ads:17.2.0'
}
AndroidManifest.xml
<manifest>
    <application>
        <!-- Sample AdMob App ID: ca-app-pub-3940256099942544~3347511713 -->
        <meta-data
            android:name="com.google.android.gms.ads.APPLICATION_ID"
            android:value="YOUR_ADMOB_APP_ID"/>
    </application>
</manifest>

##	初始化 MobileAds
在应用启动时执行
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    ...
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "YOUR_ADMOB_APP_ID");
    }
    ...
}

##	横幅广告
布局
<com.google.android.gms.ads.AdView
    xmlns:ads="http://schemas.android.com/apk/res-auto"
    android:id="@+id/adView"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_centerHorizontal="true"
    android:layout_alignParentBottom="true"
    ads:adSize="BANNER"
    ads:adUnitId="ca-app-pub-3940256099942544/6300978111">
</com.google.android.gms.ads.AdView>

加载广告:在主线程中进行对移动广告 SDK 的所有调用
mAdView = findViewById(R.id.adView);
AdRequest adRequest = new AdRequest.Builder().build();
mAdView.loadAd(adRequest);

智能横幅
android:layout_width="match_parent"
android:layout_height="wrap_content"
ads:adSize="SMART_BANNER"





