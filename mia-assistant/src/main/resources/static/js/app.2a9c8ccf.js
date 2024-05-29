(function(){"use strict";var t={22373:function(t,s,e){var a=e(66848),n=e(50788),i=e.n(n);let r={timeout:6e4,withCredentials:!0};const o=i().create(r);o.interceptors.request.use((function(t){return t}),(function(t){return Promise.reject(t)})),o.interceptors.response.use((function(t){return t}),(function(t){return Promise.reject(t)})),Plugin.install=function(t,s){t.axios=o,window.axios=o,Object.defineProperties(t.prototype,{axios:{get(){return o}},$axios:{get(){return o}}})},a["default"].use(Plugin);var l=function(){var t=this,s=t._self._c;return s("div",{attrs:{id:"app"}},[t._m(0),s("Chat")],1)},c=[function(){var t=this,s=t._self._c;return s("div",{staticClass:"title-box"},[s("div",{staticClass:"cyberpunk-text"},[t._v("医保稽核智能助手")])])}],u=function(){var t=this,s=t._self._c;return s("div",{staticClass:"chat-content"},[s("div",{ref:"mainRef",staticClass:"main-area"},[s("div",{directives:[{name:"scroll-to-bottom",rawName:"v-scroll-to-bottom"}],ref:"answerRef",staticClass:"answer-box",attrs:{id:"answer-box"}},[0===t.QAList.length&&"PROCESS"!=t.processData.status?s("div",{staticClass:"answer-item",staticStyle:{"margin-top":"50px"}},[t._m(0)]):t._e(),t._l(t.QAList,(function(e){return s("div",{key:e.chatId,staticClass:"answer-item"},[s("div",{staticClass:"item-content ask-ctx"},[t._m(1,!0),s("div",{staticClass:"ctx-box"},[t._v(t._s(e.askCtx))])]),s("div",{staticClass:"item-content ans-ctx"},[t._m(2,!0),s("div",{staticClass:"ctx-box"},[t._v(t._s(e.ansCtx)+" "),e.replyData&&e.replyData.length>0?s("div",{staticClass:"table-ctx-inner"},[s("DynamicTable",{attrs:{headers:e.dataSchema,listData:e.replyData}})],1):t._e()])])])})),"PROCESS"===t.processData.status?s("div",{staticClass:"answer-item"},[s("div",{staticClass:"item-content ask-ctx"},[t._m(3),s("div",{staticClass:"ctx-box"},[t._v(t._s(t.processData.askCtx))])]),s("div",{staticClass:"item-content ans-ctx"},[t._m(4),s("div",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],staticClass:"ctx-box"},[t._v(t._s(t.processData.ansCtx)+" ")])])]):t._e(),t.QAList.length>0&&"PROCESS"!==t.processData.status&&t.suggesList.length>0?s("div",{staticClass:"sugges-area"},[s("div",{staticClass:"sugges-title"},[t._v("你可以这样问我：")]),s("div",{staticClass:"sugges-list"},t._l(t.suggesList,(function(e,a){return s("div",{key:a,staticClass:"sugges-item",on:{click:function(s){return t.subSgs(e)}}},[s("i",[t._v(t._s(e))])])})),0)]):t._e()],2),s("AskBox",{staticClass:"ask-box",attrs:{isAvailable:"PROCESS"!==t.processData.status},on:{subMsg:t.subMsg}})],1)])},d=[function(){var t=this,s=t._self._c;return s("div",{staticClass:"item-content ans-ctx"},[s("div",{staticClass:"avatar"},[s("i",{staticClass:"el-icon-s-platform"})]),s("div",{staticClass:"ctx-box",staticStyle:{padding:"12px 24px"}},[s("h2",[t._v("你好，我是医保稽核智能助手")]),s("p",[t._v("作为一名优秀的医保稽核助理，我有非常丰富的医保稽核经验，能协助你完成相关稽核规则的生成和校验，并输出你所需要的病例数据,同时作为大模型的探索产物,我能进行一定程度的推理和计算，帮你答疑解惑。")]),s("p",[t._v("想知道我的能力如何？点这里快速上手！持续提问相关问题可以帮助优化问题结果哦.")]),s("p",[s("b",[t._v("你可以试着问我：")])]),s("li",[t._v("1.生成超标准收费规则住院期间进行阑尾炎手术次数超过10次?")]),s("li",[t._v("2.生成重复收费规则住院期间一级护理与二级护理?")]),s("li",[t._v("3.生成串项目收费规则血常规检查?")])])])},function(){var t=this,s=t._self._c;return s("div",{staticClass:"avatar"},[s("i",{staticClass:"el-icon-s-custom"})])},function(){var t=this,s=t._self._c;return s("div",{staticClass:"avatar"},[s("i",{staticClass:"el-icon-s-platform"})])},function(){var t=this,s=t._self._c;return s("div",{staticClass:"avatar"},[s("i",{staticClass:"el-icon-s-custom"})])},function(){var t=this,s=t._self._c;return s("div",{staticClass:"avatar"},[s("i",{staticClass:"el-icon-s-platform"})])}],p=(e(44114),function(){var t=this,s=t._self._c;return s("div",{staticClass:"ask-box"},[s("textarea",{directives:[{name:"model",rawName:"v-model",value:t.askContent,expression:"askContent"}],ref:"askInputRef",staticClass:"ask-input",attrs:{placeholder:"请输入问题, 换行使用Ctrl+Enter"},domProps:{value:t.askContent},on:{keydown:function(s){return!s.type.indexOf("key")&&t._k(s.keyCode,"enter",13,s.key,"Enter")?null:(s.preventDefault(),t.handleTextareaKeydown.apply(null,arguments))},input:function(s){s.target.composing||(t.askContent=s.target.value)}}}),s("el-button",{staticClass:"send-btn",attrs:{icon:"el-icon-s-promotion",round:"",type:"primary",disabled:!t.isAvailable},on:{click:t.submitMsg}})],1)}),h=[],f={props:{isAvailable:{type:Boolean,default:!0}},data(){return{askContent:""}},methods:{handleTextareaKeydown(t){if(console.log(t),"Enter"===t.key&&t.ctrlKey){t.preventDefault(),this.$refs.askInputRef.value+="\n";const{selectionStart:s,selectionEnd:e}=this.$refs.askInputRef;this.$refs.askInputRef.setSelectionRange(s+1,e+1)}else"Enter"!==t.key||t.ctrlKey||this.isAvailable&&this.submitMsg()},submitMsg(){const t=this.$refs.askInputRef.value.trim();t&&(this.$emit("subMsg",t),this.$refs.askInputRef.value="",this.askContent="")}}},v=f,m=e(81656),g=(0,m.A)(v,p,h,!1,null,"4a349d12",null),x=g.exports,C=function(){var t=this,s=t._self._c;return s("el-table",{staticClass:"d-table",attrs:{data:t.listData,border:"",stripe:"",size:"mini","header-row-class-name":"table-header"}},t._l(t.headers,(function(e,a){return e.visible?s("el-table-column",{key:a,attrs:{prop:e.prop,label:e.label,"min-width":e.minWidth||90}},[s("template",{slot:"header"},[e.label.length<6?[t._v(" "+t._s(e.label)+" ")]:[s("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:e.label,placement:"top"}},[s("span",[t._v(t._s(e.label.substr(0,4)+"..."))])])]],2)],2):t._e()})),1)},b=[],_={name:"Dtable",props:{listData:{type:Array,default:()=>[]},headers:{type:Array,default:()=>[]}}},y=_,k=(0,m.A)(y,C,b,!1,null,"3d479d10",null),w=k.exports,D=function(){var t=this,s=t._self._c;return s("div",{staticClass:"ans-ctx",domProps:{innerHTML:t._s(t.compiledMarkdown)}})},S=[],T=e(55852),A=e(81109);e(85827);const O=(t,s=!1)=>{t=t.trim();let e=!1;const a=(t.match(/```/g)||[]).length,n=a%2===1&&!t.endsWith("```");n&&(s?(t+="█\n```",e=!0):t+="\n```"),a&&!n&&(t=t.replace(/```$/,"\n```")),s&&!e&&(t+="█");try{let s=T.xI.parse(t);return s=s.replace(/\[\^(\d+)\^]/g,"<strong>[$1]</strong>"),s=s.replace(/\^(\d+)\^/g,"<strong>[$1]</strong>"),s}catch(i){return console.error("ERROR",i),t}};function R(t){if(!t&&"object"!==typeof t)throw new Error("error arguments","deepClone");const s=t.constructor===Array?[]:{};return Object.keys(t).forEach((e=>{t[e]&&"object"===typeof t[e]?s[e]=R(t[e]):s[e]=t[e]})),s}var E={props:{answerCtx:{type:String,default:"..."}},data(){return{readText:"",markdown:""}},watch:{answerCtx(t){this.typeText(t)}},computed:{compiledMarkdown(){return this.markdown}},mounted(){const t=()=>{document.querySelectorAll("pre code").forEach((t=>{A.A.highlightBlock(t)}))};this.$nextTick((()=>{t()})),this.typeText(this.answerCtx)},methods:{highlightCodeBlocks(){document.querySelectorAll("pre code").forEach((t=>{A.A.highlightBlock(t)}))},typeText(t){this.readText="";let s=0,e=setInterval((()=>{s<t.length?(this.readText+=t.charAt(s),this.markdown=O(this.readText,!1),s++):(clearInterval(e),this.highlightCodeBlocks())}),30)}}},I=E,M=(0,m.A)(I,D,S,!1,null,"351c4f60",null),$=M.exports,P={name:"Chat",components:{AskBox:x,DynamicTable:w,AnsCtxBox:$},data(){return{cnsvId:"",askCtx:"rwer\nasfasdd\n中文大撒大撒啊实打实的\n打赏啊实打实打算s",markdown:'# Hello, Markdown!\n\nThis is a _sample_ of **Markdown** text.\n\n```javascript\nconsole.log("Hello, World!");\n```\n',QAList:[],submitCtx:"",suggesList:[],processData:{chatId:"",status:"DONE",askCtx:"",ansCtx:"\n",dataSchema:[],replyData:[]},loading:!1}},watch:{processData:{deep:!0,handler(){this.scrollToBottom()}}},methods:{subMsg(t){this.submitCtx=t,this.sendMessage()},subSgs(t){this.submitCtx=t,this.sendMessage()},async sendMessage(){const t=(new Date).getTime().toString(16);this.processData.chatId=t,this.processData.askCtx=this.submitCtx,this.processData.ansCtx="\n",this.processData.status="PROCESS",this.processData.dataSchema=[],this.processData.replyData=[],this.loading=!0,this.$axios({url:"/mia/prompt",method:"post",data:{id:t,appToke:"123",userId:"zhangsan",question:this.submitCtx}}).then((t=>{if(this.loading=!1,t.data.success){const s=t.data.data;s.cnsvId&&(this.cnsvId=s.cnsvId),this.typeText(s.summary||"\n"),this.suggesList=s.suggestList||[],200===t.data.code&&(this.processData.dataSchema=s.dataSchema,this.processData.replyData=s.replyData)}})).catch((t=>{this.loading=!1,this.processData.status="ERROR",console.log(t),this.$message.error(t.message||"服务异常")}))},typeText(t){this.processData.ansCtx="";let s=0,e=setInterval((()=>{s<t.length?(this.processData.ansCtx+=t.charAt(s),s++):(clearInterval(e),this.ansEnd())}),30)},ansEnd(){this.processData.status="DONE",this.QAList.push(R(this.processData))},scrollToBottom(){console.log("scrollToBottom"),this.$nextTick((()=>{this.$refs.answerRef.scrollTop=this.$refs.answerRef.scrollHeight}))}}},j=P,B=(0,m.A)(j,u,d,!1,null,"07539244",null),L=B.exports,H={name:"App",components:{Chat:L}},N=H,Q=(0,m.A)(N,l,c,!1,null,"04ce506a",null),q=Q.exports,K=e(89143),W=e.n(K);a["default"].config.productionTip=!1,a["default"].use(W()),a["default"].directive("scroll-to-bottom",{inserted:function(t){t.scrollTop=t.scrollHeight}}),new a["default"]({render:t=>t(q)}).$mount("#app")}},s={};function e(a){var n=s[a];if(void 0!==n)return n.exports;var i=s[a]={id:a,loaded:!1,exports:{}};return t[a].call(i.exports,i,i.exports,e),i.loaded=!0,i.exports}e.m=t,function(){e.amdO={}}(),function(){var t=[];e.O=function(s,a,n,i){if(!a){var r=1/0;for(u=0;u<t.length;u++){a=t[u][0],n=t[u][1],i=t[u][2];for(var o=!0,l=0;l<a.length;l++)(!1&i||r>=i)&&Object.keys(e.O).every((function(t){return e.O[t](a[l])}))?a.splice(l--,1):(o=!1,i<r&&(r=i));if(o){t.splice(u--,1);var c=n();void 0!==c&&(s=c)}}return s}i=i||0;for(var u=t.length;u>0&&t[u-1][2]>i;u--)t[u]=t[u-1];t[u]=[a,n,i]}}(),function(){e.n=function(t){var s=t&&t.__esModule?function(){return t["default"]}:function(){return t};return e.d(s,{a:s}),s}}(),function(){e.d=function(t,s){for(var a in s)e.o(s,a)&&!e.o(t,a)&&Object.defineProperty(t,a,{enumerable:!0,get:s[a]})}}(),function(){e.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(t){if("object"===typeof window)return window}}()}(),function(){e.o=function(t,s){return Object.prototype.hasOwnProperty.call(t,s)}}(),function(){e.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})}}(),function(){e.nmd=function(t){return t.paths=[],t.children||(t.children=[]),t}}(),function(){var t={524:0};e.O.j=function(s){return 0===t[s]};var s=function(s,a){var n,i,r=a[0],o=a[1],l=a[2],c=0;if(r.some((function(s){return 0!==t[s]}))){for(n in o)e.o(o,n)&&(e.m[n]=o[n]);if(l)var u=l(e)}for(s&&s(a);c<r.length;c++)i=r[c],e.o(t,i)&&t[i]&&t[i][0](),t[i]=0;return e.O(u)},a=self["webpackChunkchat_web"]=self["webpackChunkchat_web"]||[];a.forEach(s.bind(null,0)),a.push=s.bind(null,a.push.bind(a))}();var a=e.O(void 0,[504],(function(){return e(22373)}));a=e.O(a)})();
//# sourceMappingURL=app.2a9c8ccf.js.map