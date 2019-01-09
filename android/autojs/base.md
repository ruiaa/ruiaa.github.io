//暂停5毫秒
sleep(5000);

setClip("剪贴板文本");
getClip()

toast(message)
toastLog(message) //toast(message);log(message)

waitForPackage(package[, period = 200])

runtime.loadJar(path)
path {string} jar文件路径
加载目标jar文件，加载成功后将可以使用该Jar文件的类。

// 加载jsoup.jar
runtime.loadJar("./jsoup.jar");
// 使用jsoup解析html
importClass(org.jsoup.Jsoup);
log(Jsoup.parse(files.read("./test.html")));











