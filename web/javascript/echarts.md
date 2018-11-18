#   使用
+   引入  <script src="echarts.min.js"></script>
+   为 ECharts 准备一个具备高宽的 DOM 容器

        <div id="main" style="width: 600px;height:400px;"></div>

+   初始化 echarts 实例

        var myChart = echarts.init(document.getElementById('main'));

+   指定图表的配置项和数据

        var option = {
            title: {
                text: 'ECharts 入门示例'
            },
            ...
        };

+   使用配置项和数据显示图表

        myChart.setOption(option);


#   配置选项和数据
    var option = {
        title: {
            text: 'ECharts 入门示例'
        },
        tooltip: {},
        legend: {
            data:['销量']
        },
        xAxis: {
            data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
        },
        yAxis: {},
        series: [{
            name: '销量',
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20]
        }]
    };
    
+   title
+   tooltip
+   legend
+   xAxis
+   yAxis
+   series：Array，系列列表。每个系列通过 type 决定自己的图表类型
    +   name：该套数据名称
    +   type：图表类型：line,bar,pie,scatter(散点图),effectScatter,radar(雷达图),treemap(层级数据,树状数据),
    +   data：数据，Array






+   title标题组件 
    +   text
    +   link
+   legend 图例组件
    +   type： 'plain'：普通图例，'scroll'：可滚动翻页的图例。当图例数量较多时可以使用。
    +   data：图例的数据数组，数组项通常为一个字符串，每一项代表一个系列的 name
        +   name
        +   icon：'circle', 'rect', 'roundRect', 'triangle', 'diamond', 'pin', 'arrow'
        +   textStyle
    +   textStyle：图例的公用文本样式
+   grid：直角坐标系内绘图网格
+   xAxis：x轴
    +   name
    +   gridIndex：x 轴所在的 grid 的索引，默认位于第一个 grid。
    +   type：
        +   'value' 数值轴，适用于连续数据。
        +   'category' 类目轴，适用于离散的类目数据，为该类型时必须通过 data 设置类目数据。
        +   'time' 时间轴，适用于连续的时序数据，与数值轴相比时间轴带有时间的格式化
        +   'log' 对数轴。适用于对数数据。
    +   data：在类目轴（type: 'category'）中有效
+   yAxis


+   tooltip ：提示框组件
    +   trigger：触发类型。   'item'数据项图形触发，'axis'坐标轴触发，'none'
    +   axisPointer：坐标轴指示器配置项。
    
+   toolbox：工具栏
    +   内置有导出图片，数据视图，动态类型切换，数据区域缩放，重置五个工具
    +   feature:saveAsImage,restore,dataView,dataZoom,magicType
+   brush：区域选择组件，用户可以选择图中一部分数据，从而便于向用户展示被选中数据，或者他们的一些统计计算结果。
+   visualMap视觉映射组件 
        
        图形类别（symbol）
        图形大小（symbolSize）
        颜色（color）
        透明度（opacity）
        颜色透明度（colorAlpha）
        颜色明暗度（colorLightness）
        颜色饱和度（colorSaturation）
        色调（colorHue）
        
        visualMap 组件定义了把数据的『哪个维度』映射到『什么视觉元素上』
        series: {
            data: [
                {                        // 这里每一个项就是数据项（dataItem）
                    value: [3434, 129,  '圣马力诺'], // 这是数据项的数据值（value）
                    itemStyle: {...}
                },
                [1212, 5454, '梵蒂冈'],   // 也可以直接是 dataItem 的 value，这更常见。
                [2323, 3223, '瑙鲁'],     // 每个 value 都是『三维』的，每列是一个维度。
                [4343, 23,   '图瓦卢']    // 假如是『气泡图』，常见第一维度映射到x轴，
                                         // 第二维度映射到y轴，
                                         // 第三维度映射到气泡半径（symbolSize）
            ]
        }
        
        
        option = {
            visualMap: [ // 可以同时定义多个 visualMap 组件。
                { // 第一个 visualMap 组件
                    type: 'continuous', // 定义为连续型 viusalMap
                    ...
                },
                { // 第二个 visualMap 组件
                    type: 'piecewise', // 定义为分段型 visualMap
                    ...
                }
            ],
            ...
        };

+   dataZoom数据区域缩放组件 :对数轴（axis）进行『数据窗口缩放』『数据窗口平移』操作

        option = {
            ...,
            dataZoom: [
                {
                    type: 'slider',     // 这个 dataZoom 组件是 slider(轴滑块) 型 dataZoom 组件
                    xAxisIndex: 0,      // 控制第0个x轴
                    start: 10,          // 左边在 10% 的位置。
                    end: 60             // 右边在 60% 的位置。
                },
                {
                    type: 'inside',     // 这个 dataZoom 组件是 inside(鼠标滚轮) 型 dataZoom 组件
                    xAxisIndex: 0,
                    start: 10,
                    end: 60
                },
                {
                    type: 'slider',
                    yAxisIndex: 0,
                    start: 30,
                    end: 80
                },
                {
                    type: 'inside',
                    yAxisIndex: 0,
                    start: 30,
                    end: 80
                }
            ],
            ...
        }
+   timeline时间线组件 



#   异步

    var myChart = echarts.init(document.getElementById('main'));
    // 显示标题，图例和空的坐标轴
    myChart.setOption({
        title: {
            text: '异步数据加载示例'
        },
        tooltip: {},
        legend: {
            data:['销量']
        },
        xAxis: {
            data: []
        },
        yAxis: {},
        series: [{
            name: '销量',
            type: 'bar',
            data: []
        }]
    });
    
    //loading 动画
    myChart.showLoading();
    
    
    // 异步加载数据
    $.get('data.json').done(function (data) {
        myChart.hideLoading();
        // 填入数据
        myChart.setOption({
            xAxis: {
                data: data.categories
            },
            series: [{
                // 根据名字对应到相应的系列
                name: '销量',
                data: data.data
            }]
        });
    });
    
    //动态更新
    setInterval(function () {
        //如果只需要加入单个数据，可以先 data.push(value) 后 setOption
        updateData();
        myChart.setOption({
            xAxis: {
                data: date
            },
            series: [{
                name:'成交',
                data: data
            }]
        });
    }, 500);

















