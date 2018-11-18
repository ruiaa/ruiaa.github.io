+   File | Settings | Editor | Live Templates   编辑常用的模板代码
    +   

+   LayoutFormatter             格式化layout     
    +   PopupMenu(Right Click) -> Refactor -> Reformat Layout XML
    
+   Lifecycle Sorter            排序Activity或者fragment的生命周期方法位置
    +   Ctrl + alt + K
    
+   GsonFormat                  javabean          
    +   Alt+Insert -> GsonFormat 注意 设置SerializedName和public field 一般只读
    
+   Parcelable code generator   JavaBean序列化，快速实现Parcelable接口
    +   Alt+Insert -> Parcelable （Parcelable用于内存高效传输数据，Serializable用于数据持久化和网络传输）
    
+   ButterKnife Zelezny         ButterKnife
    +   选中布局xml的资源id -> Alt+Insert -> Generate ButterKnife Injections 禁用onCLick 注意重命名
    
+   Android Code Generator      布局文件快速生成对应的Activity，Fragment，Adapter，Menu
    +   右键 -> Generator Android Code 
    
+   ECTranslation               英文 -> 中文
    +   选中单词 -> Edit -> Translate (更改快捷键 File->setting->KeyMap->search Translate)
    
+   TranslationPlugin           中英互译、单词朗读
    +   https://github.com/YiiGuxing/TranslationPlugin
    
+   Exynap                      自动生成样板代码
    +   
+   

+   5o3NUYG6-j-hOixCNuC5PM8Ie2x4GgAJ














#   SpannableStringBuilder,SpannableString : setSpan(Object what, int start, int end, int flags)
+   start： 指定Span的开始位置
+   end： 指定Span的结束位置，并不包括这个位置。
+   flags：取值有如下四个
    +   Spannable.span_inclusive_exclusive：前面包括，后面不包括，即在文本前插入新的文本会应用该样式，而在文本后插入新文本不会应用该样式
    +   Spannable.span_inclusive_inclusive：前面包括，后面包括，即在文本前插入新的文本会应用该样式，而在文本后插入新文本也会应用该样式
    +   Spannable.span_exclusive_exclusive：前面不包括，后面不包括
    +   Spannable.span_exclusive_inclusive：前面不包括，后面包括
+   what： 对应的各种Span，不同的Span对应不同的样式。已知的可用类有：

    +   StrikethroughSpan : 删除线
    +   UnderlineSpan : 下划线
    +   StyleSpan : 字体样式：粗体、斜体等
    
    +   AbsoluteSizeSpan : 文本字体（绝对大小）
    +   RelativeSizeSpan : 相对大小（文本字体）
    +   BackgroundColorSpan : 文本背景色
    +   ForegroundColorSpan : 文本颜色
    
    +   MaskFilterSpan : 修饰效果，如模糊(BlurMaskFilter)浮雕
    +   RasterizerSpan : 光栅效果
    +   SuggestionSpan : 相当于占位符
    +   DynamicDrawableSpan : 设置图片，基于文本基线或底部对齐。
    +   ImageSpan : 图片
    +   ScaleXSpan : 基于x轴缩放
    +   SubscriptSpan : 下标（数学公式会用到）
    +   SuperscriptSpan : 上标（数学公式会用到）
    +   TextAppearanceSpan : 文本外貌（包括字体、大小、样式和颜色）
    +   TypefaceSpan : 文本字体
    +   URLSpan : 文本超链接
    +   ClickableSpan : 点击事件























