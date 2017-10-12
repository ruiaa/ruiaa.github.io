#   Transition
+   初始状态（已知）--> 定义过渡 --> 最终状态（已知）， 需要先有事件触发元素的状态变化
+   transition-property: height;        作用于元素的某个属性
+   transition-duration: 1s;            过渡持续时间
+   transition-delay: 1s;               相对于变化的开始时刻，延迟的时间
+   transition-timing-function: ease;   插值器类型：linear：匀速,ease-in：加速,ease-out：减速,cubic-bezier函数：自定义速度模式

        img{
            transition: 1s width, 1s 1s height ease;
        }
        img{
            transition-property: height;
            transition-duration: 1s;
            transition-delay: 1s;
            transition-timing-function: ease;
        }
   
#   animation
+   指定动画一个周期持续的时间，以及动画效果的名称
+   用keyframes关键字，定义动画效果
    
        div:hover {
          animation: 1s rainbow;
        }
        
        @keyframes rainbow {
          0% { background: #c00; }
          50% { background: orange; }
          100% { background: yellowgreen; }
        }
        //0%可以用from代表，100%可以用to代表,如果省略某个状态，浏览器会自动推算
        
        div:hover {
          animation: 1s 1s rainbow linear 3 forwards normal;
        }
        div:hover {
          animation-duration: 1s;
          animation-delay: 1s;
          animation-name: rainbow;
          animation-timing-function: linear;
          animation-iteration-count: 3;          
          animation-fill-mode:forwards;
          animation-direction: normal;
        }


+   指定动画具体播放的次数(infinite:无限)  :  animation: 1s rainbow 3;
+   animation-fill-mode  :  动画结束后显示的状态
    +   forwards保持在结束状态  :  animation: 1s rainbow forwards;
    +   none：默认值，回到动画没开始时的状态。
    +   backwards：让动画回到第一帧的状态。
    +   both: 根据animation-direction轮流应用forwards和backwards规则
    
+   animation-direction  :  动画播放的方向
    +   normal 正放 （正放+正放+正放）
    +   alternate 正向循环 （正放+倒放+正放+倒放）
    +   reverse 倒放
    +   alternate-reverse 倒向循环 （倒放+正放+倒放+正放）
    
+   keyframes  定义动画的各个状态

+   steps(10) 步进
+   animation-play-state ： 动画播放过程中突然停止，显示状态：默认跳回到动画的开始状态， paused 暂停， running继续
