(function(){var r=this;var e;if(typeof require=="function"){try{var n=require("crypto").randomBytes;e=n&&function(){return n(16)}}catch(a){}}if(!e&&r.crypto&&crypto.getRandomValues){var t=new Uint8Array(16);e=function B(){crypto.getRandomValues(t);return t}}if(!e){var u=new Array(16);e=function(){for(var r=0,e;r<16;r++){if((r&3)===0)e=Math.random()*4294967296;u[r]=e>>>((r&3)<<3)&255}return u}}var o=typeof Buffer=="function"?Buffer:Array;var f=[];var i={};for(var c=0;c<256;c++){f[c]=(c+256).toString(16).substr(1);i[f[c]]=c}function v(r,e,n){var a=e&&n||0,t=0;e=e||[];r.toLowerCase().replace(/[0-9a-f]{2}/g,function(r){if(t<16){e[a+t++]=i[r]}});while(t<16){e[a+t++]=0}return e}function s(r,e){var n=e||0,a=f;return a[r[n++]]+a[r[n++]]+a[r[n++]]+a[r[n++]]+"-"+a[r[n++]]+a[r[n++]]+"-"+a[r[n++]]+a[r[n++]]+"-"+a[r[n++]]+a[r[n++]]+"-"+a[r[n++]]+a[r[n++]]+a[r[n++]]+a[r[n++]]+a[r[n++]]+a[r[n++]]}var l=e();var d=[l[0]|1,l[1],l[2],l[3],l[4],l[5]];var y=(l[6]<<8|l[7])&16383;var m=0,p=0;function w(r,e,n){var a=e&&n||0;var t=e||[];r=r||{};var u=r.clockseq!=null?r.clockseq:y;var o=r.msecs!=null?r.msecs:(new Date).getTime();var f=r.nsecs!=null?r.nsecs:p+1;var i=o-m+(f-p)/1e4;if(i<0&&r.clockseq==null){u=u+1&16383}if((i<0||o>m)&&r.nsecs==null){f=0}if(f>=1e4){throw new Error("uuid.v1(): Can't create more than 10M uuids/sec")}m=o;p=f;y=u;o+=122192928e5;var c=((o&268435455)*1e4+f)%4294967296;t[a++]=c>>>24&255;t[a++]=c>>>16&255;t[a++]=c>>>8&255;t[a++]=c&255;var v=o/4294967296*1e4&268435455;t[a++]=v>>>8&255;t[a++]=v&255;t[a++]=v>>>24&15|16;t[a++]=v>>>16&255;t[a++]=u>>>8|128;t[a++]=u&255;var l=r.node||d;for(var w=0;w<6;w++){t[a+w]=l[w]}return e?e:s(t)}function g(r,n,a){var t=n&&a||0;if(typeof r=="string"){n=r=="binary"?new o(16):null;r=null}r=r||{};var u=r.random||(r.rng||e)();u[6]=u[6]&15|64;u[8]=u[8]&63|128;if(n){for(var f=0;f<16;f++){n[t+f]=u[f]}}return n||s(u)}var h=g;h.v1=w;h.v4=g;h.parse=v;h.unparse=s;h.BufferClass=o;if(r.define&&define.amd){define(function(){return h})}else if(typeof module!="undefined"&&module.exports){module.exports=h}else{var q=r.uuid;h.noConflict=function(){r.uuid=q;return h};r.uuid=h}})();