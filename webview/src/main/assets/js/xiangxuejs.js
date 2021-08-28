var xiangxuejs = {};
xiangxuejs.os = {};
xiangxuejs.os.isIOS = /iOS|iPhone|iPad|iPod/i.test(navigator.userAgent);
xiangxuejs.os.isAndroid = !xiangxuejs.os.isIOS;
xiangxuejs.callbacks = {}

xiangxuejs.callback = function (callbackname, response) {
   var callbackobject = xiangxuejs.callbacks[callbackname];
   console.log("xxxx"+callbackname);
   if (callbackobject !== undefined){
       if(callbackobject.callback != undefined){
          console.log("xxxxxx"+response);
           var ret = callbackobject.callback(response);
           if(ret === false){
               return
           }
           delete xiangxuejs.callbacks[callbackname];
       }
   }
}

xiangxuejs.takeNativeAction = function(commandname, parameters){
    console.log("xiangxuejs takenativeaction")
    var request = {};
    request.name = commandname;
    request.param = parameters;
    if(window.xiangxuejs.os.isAndroid){
        console.log("android take native action" + JSON.stringify(request));
        window.kukiwebview.takeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.kukiwebview.postMessage(JSON.stringify(request))
    }
}

//传给WebView数据
xiangxuejs.takeNativeActionWithCallback = function(commandname, parameters,callback){
 console.log("xiangxuejs takeNativeActionWithCallback")

    /*定义回调的名称*/
 var callbackname = "nativetojs_callback_" +  (new Date()).getTime() + "_" + Math.floor(Math.random() * 10000);
    xiangxuejs.callbacks[callbackname] = {callback:callback};


    var request = {};
    request.name = commandname;
    request.param = parameters;
    //将回调的名称传给WebView
    request.param.callbackname=callbackname;
    if(window.xiangxuejs.os.isAndroid){
        window.kukiwebview.takeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.kukiwebview.postMessage(JSON.stringify(request))
    }
}

window.xiangxuejs = xiangxuejs;
