#   接口
+   bokeh.models  低级接口，能为app开发者提供高度灵活的图形表示（可以自定义一些顶层的组件）
+   bokeh.plotting  中级接口，该接口主要用于绘制曲线（默认加载一些低级的组件）
+   bokeh.charts 高级接口，能快速简单地构建复杂的图形

#   bokeh.plotting接口创建图表

    # 准备数据
    x = [1, 2, 3, 4, 5]
    y = [6, 7, 2, 4, 5]
    
    # 指定输出
    output_file("lines.html")
    
    # 创建图表容器，配置整体参数(title、tools和axes labels)
    p = figure(title="simple line example", x_axis_label='x', y_axis_label='y')
    
    # 绘制
    p.line(x, y, legend="Temp.", line_width=2)
    
    # 显示show() 或 保存save()
    show(p)