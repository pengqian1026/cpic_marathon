(function(){"use strict";var t={6445:function(t,s,e){var a=e(6848),i=e(788),n=e.n(i);let r={timeout:6e4,withCredentials:!0};const o=n().create(r);o.interceptors.request.use((function(t){return t}),(function(t){return Promise.reject(t)})),o.interceptors.response.use((function(t){return t}),(function(t){return Promise.reject(t)})),Plugin.install=function(t,s){t.axios=o,window.axios=o,Object.defineProperties(t.prototype,{axios:{get(){return o}},$axios:{get(){return o}}})},a["default"].use(Plugin);var l=function(){var t=this,s=t._self._c;return s("div",{attrs:{id:"app"}},[t._m(0),s("leftPlace",{staticClass:"left-box",attrs:{QAHistoryList:t.QAHistoryList},on:{subHistory:t.chatChoose,addNewChat:t.addNewChat}}),s("rightPlace",{staticClass:"right-box"}),t.showChat?s("Chat",{ref:"chatRef",attrs:{setQAList:t.setQAList}}):t._e()],1)},c=[function(){var t=this,s=t._self._c;return s("div",{staticClass:"title-box"},[s("div",{staticClass:"cyberpunk-text"},[t._v("医保稽核智能助手Alpha")])])}],u=function(){var t=this,s=t._self._c;return s("div",{staticClass:"chat-content"},[s("div",{ref:"mainRef",staticClass:"main-area"},[s("div",{directives:[{name:"scroll-to-bottom",rawName:"v-scroll-to-bottom"}],ref:"answerRef",staticClass:"answer-box",attrs:{id:"answer-box"}},[0===t.QAList.length&&"PROCESS"!=t.processData.status?s("div",{staticClass:"answer-item",staticStyle:{"margin-top":"50px"}},[t._m(0)]):t._e(),t._l(t.QAList,(function(e){return s("div",{key:e.chatId,staticClass:"answer-item"},[s("div",{staticClass:"item-content ask-ctx"},[t._m(1,!0),s("div",{staticClass:"ctx-box"},[t._v(t._s(e.askCtx))])]),s("div",{staticClass:"item-content ans-ctx"},[t._m(2,!0),s("div",{staticClass:"ctx-box"},[t._v(t._s(e.ansCtx)+" "),e.replyData&&e.replyData.length>0?s("div",{staticClass:"table-ctx-inner"},[s("DynamicTable",{attrs:{headers:e.dataSchema,listData:e.replyData}})],1):t._e()])])])})),"PROCESS"===t.processData.status?s("div",{staticClass:"answer-item"},[s("div",{staticClass:"item-content ask-ctx"},[t._m(3),s("div",{staticClass:"ctx-box"},[t._v(t._s(t.processData.askCtx))])]),s("div",{staticClass:"item-content ans-ctx"},[t._m(4),s("div",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],staticClass:"ctx-box"},[t._v(t._s(t.processData.ansCtx)+" ")])])]):t._e(),t.QAList.length>0&&"PROCESS"!==t.processData.status&&t.suggesList.length>0?s("div",{staticClass:"sugges-area"},[s("div",{staticClass:"sugges-title"},[t._v("你可以这样问我：")]),s("div",{staticClass:"sugges-list"},t._l(t.suggesList,(function(e,a){return s("div",{key:a,staticClass:"sugges-item",on:{click:function(s){return t.subSgs(e)}}},[s("i",[t._v(t._s(e))])])})),0)]):t._e()],2),s("AskBox",{staticClass:"ask-box",attrs:{isAvailable:"PROCESS"!==t.processData.status},on:{subMsg:t.subMsg}})],1)])},d=[function(){var t=this,s=t._self._c;return s("div",{staticClass:"item-content ans-ctx"},[s("div",{staticClass:"avatar"},[s("i",{staticClass:"el-icon-s-platform"})]),s("div",{staticClass:"ctx-box",staticStyle:{padding:"12px 24px"}},[s("h2",[t._v("你好，我是医保稽核智能助手")]),s("p",[t._v("作为一名优秀的医保稽核助理，我有非常丰富的医保稽核经验，能协助你完成相关稽核规则的生成和校验，并输出你所需要的病例数据,同时作为大模型的探索产物,我能进行一定程度的推理和计算，帮你答疑解惑。 ")]),s("p",[t._v("想知道我的能力如何？点这里快速上手！持续提问相关问题可以帮助优化问题结果哦.")]),s("p",[s("b",[t._v("你可以试着问我：")])]),s("li",[t._v("1.生成超标准收费规则住院期间进行阑尾炎手术次数超过10次?")]),s("li",[t._v("2.生成重复收费规则住院期间一级护理与二级护理?")]),s("li",[t._v("3.生成串项目收费规则血常规检查?")])])])},function(){var t=this,s=t._self._c;return s("div",{staticClass:"avatar"},[s("i",{staticClass:"el-icon-s-custom"})])},function(){var t=this,s=t._self._c;return s("div",{staticClass:"avatar"},[s("i",{staticClass:"el-icon-s-platform"})])},function(){var t=this,s=t._self._c;return s("div",{staticClass:"avatar"},[s("i",{staticClass:"el-icon-s-custom"})])},function(){var t=this,s=t._self._c;return s("div",{staticClass:"avatar"},[s("i",{staticClass:"el-icon-s-platform"})])}],h=(e(4114),function(){var t=this,s=t._self._c;return s("div",{staticClass:"ask-box"},[s("textarea",{directives:[{name:"model",rawName:"v-model",value:t.askContent,expression:"askContent"}],ref:"askInputRef",staticClass:"ask-input",attrs:{placeholder:"请输入问题, 换行使用Shift+Enter"},domProps:{value:t.askContent},on:{keydown:function(s){return!s.type.indexOf("key")&&t._k(s.keyCode,"enter",13,s.key,"Enter")?null:(s.preventDefault(),t.handleTextareaKeydown.apply(null,arguments))},input:function(s){s.target.composing||(t.askContent=s.target.value)}}}),s("el-button",{staticClass:"send-btn",attrs:{icon:"el-icon-s-promotion",round:"",type:"primary",disabled:!t.isAvailable},on:{click:t.submitMsg}})],1)}),f=[],p={props:{isAvailable:{type:Boolean,default:!0}},data(){return{askContent:""}},methods:{handleTextareaKeydown(t){if(console.log(t),"Enter"===t.key&&t.shiftKey){t.preventDefault(),this.$refs.askInputRef.value+="\n";const{selectionStart:s,selectionEnd:e}=this.$refs.askInputRef;this.$refs.askInputRef.setSelectionRange(s+1,e+1)}else"Enter"!==t.key||t.shiftKey||this.isAvailable&&this.submitMsg()},submitMsg(){const t=this.$refs.askInputRef.value.trim();t&&(this.$emit("subMsg",t),this.$refs.askInputRef.value="",this.askContent="")}}},v=p,m=e(1656),C=(0,m.A)(v,h,f,!1,null,"87d60b98",null),g=C.exports,b=function(){var t=this,s=t._self._c;return s("el-table",{staticClass:"d-table",attrs:{data:t.listData,border:"",stripe:"",size:"mini","header-row-class-name":"table-header"}},t._l(t.headers,(function(e,a){return e.visible?s("el-table-column",{key:a,attrs:{prop:e.prop,label:e.label,"min-width":e.minWidth||90}},[s("template",{slot:"header"},[e.label.length<6?[t._v(" "+t._s(e.label)+" ")]:[s("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:e.label,placement:"top"}},[s("span",[t._v(t._s(e.label.substr(0,4)+"..."))])])]],2)],2):t._e()})),1)},_=[],y={name:"Dtable",props:{listData:{type:Array,default:()=>[]},headers:{type:Array,default:()=>[]}}},x=y,k=(0,m.A)(x,b,_,!1,null,"3d479d10",null),w=k.exports;e(5852);function A(t){if(!t&&"object"!==typeof t)throw new Error("error arguments","deepClone");const s=t.constructor===Array?[]:{};return Object.keys(t).forEach((e=>{t[e]&&"object"===typeof t[e]?s[e]=A(t[e]):s[e]=t[e]})),s}var D={name:"Chat",components:{AskBox:g,DynamicTable:w},props:{setQAList:{type:Array,default:()=>[]}},data(){return{cnsvId:"",askCtx:"rwer\nasfasdd\n中文大撒大撒啊实打实的\n打赏啊实打实打算s",markdown:'# Hello, Markdown!\n\nThis is a _sample_ of **Markdown** text.\n\n```javascript\nconsole.log("Hello, World!");\n```\n',QAList:[],submitCtx:"",suggesList:[],processData:{chatId:"",status:"DONE",askCtx:"",ansCtx:"\n",dataSchema:[],replyData:[]},loading:!1}},watch:{setQAList(t){t&&t.length>0?(this.QAList=t,this.cnsvId=t[0].cnsvId,this.suggesList=t[t.length-1].suggestList||[]):(this.QAList=[],this.cnsvId=""),this.scrollToBottom()},processData:{deep:!0,handler(){this.scrollToBottom()}}},methods:{subMsg(t){this.submitCtx=t,this.sendMessage()},subSgs(t){this.submitCtx=t,this.sendMessage()},async sendMessage(){const t=(new Date).getTime().toString(16);this.processData.chatId=t,this.processData.askCtx=this.submitCtx,this.processData.ansCtx="\n",this.processData.status="PROCESS",this.processData.dataSchema=[],this.processData.replyData=[],this.loading=!0,this.$axios({url:"/mia/prompt",method:"post",data:{id:t,appToke:"123",userId:"zhangsan",question:this.submitCtx,cnsvId:this.cnsvId}}).then((t=>{if(this.loading=!1,t.data.success){const s=t.data.data;s.cnsvId&&(this.cnsvId=s.cnsvId),this.typeText(s.summary||"\n"),this.suggesList=s.suggestList||[],200===t.data.code&&(this.processData.dataSchema=s.dataSchema,this.processData.replyData=s.replyData)}else this.processData.status="ERROR",this.$message.error("服务异常")})).catch((t=>{this.loading=!1,this.processData.status="ERROR",console.log(t),this.$message.error(t.message||"服务异常")}))},typeText(t){this.processData.ansCtx="";let s=0,e=setInterval((()=>{s<t.length?(this.processData.ansCtx+=t.charAt(s),s++):(clearInterval(e),this.ansEnd())}),30)},ansEnd(){this.processData.status="DONE",this.QAList.push(A(this.processData))},scrollToBottom(){console.log("scrollToBottom"),this.$nextTick((()=>{this.$refs.answerRef.scrollTop=this.$refs.answerRef.scrollHeight}))}}},S=D,O=(0,m.A)(S,u,d,!1,null,"0b5afa40",null),L=O.exports,I=function(){var t=this,s=t._self._c;return s("div",{staticClass:"history-list"},[s("div",{staticClass:"h-item add-chat",on:{click:t.addNewChat}},[s("i",{staticClass:"el-icon-plus"}),t._v(" 创建新会话 ")]),t._l(t.QAHistoryList,(function(e){return s("div",{key:e.cnsvId,staticClass:"h-item",on:{click:function(s){return t.subHistory(e)}}},[s("span",[t._v(t._s(e.miaCnsvInfoPOS[0].question))]),s("i",{staticClass:"el-icon-more"})])}))],2)},R=[],Q={props:{QAHistoryList:{type:Array,default:()=>[[]]}},data(){return{}},methods:{subHistory(t){this.$emit("subHistory",t)},addNewChat(){this.$emit("addNewChat")}}},T=Q,$=(0,m.A)(T,I,R,!1,null,"24e32da7",null),P=$.exports,E=function(){var t=this;t._self._c;return t._m(0)},H=[function(){var t=this,s=t._self._c;return s("div",{staticClass:"right-list"},[s("div",{staticClass:"rl-item"},[s("i",{staticClass:"el-icon-finished"}),s("span",[t._v("数据智能校验")])]),s("div",{staticClass:"rl-item"},[s("span",[t._v("规则引擎生成")]),s("i",{staticClass:"el-icon-discover"})]),s("div",{staticClass:"rl-item"},[s("i",{staticClass:"el-icon-data-analysis"}),s("span",[t._v("基金运行监测")])]),s("div",{staticClass:"rl-item"},[s("span",[t._v("知识库能力构建")]),s("i",{staticClass:"el-icon-connection"})])])}],j={},M=j,N=(0,m.A)(M,E,H,!1,null,"7555bd03",null),B=N.exports,q={name:"App",components:{Chat:L,leftPlace:P,rightPlace:B},data(){return{showChat:!0,setQAList:[],QAHistoryList:[]}},created(){this.getChatHistory()},methods:{async addNewChat(){0===this.$refs.chatRef.QAList.length?this.$message.error("目前会话已是新会话"):(await this.getChatHistory(),this.showChat=!1,this.setQAList=[],this.$nextTick((()=>{this.showChat=!0})))},chatChoose(t){this.setQAList=t.miaCnsvInfoPOS.map((t=>{const s=JSON.parse(t.content);return console.log(s),{...t,askCtx:t.question,ansCtx:s.summary,...s}}))},getChatHistory(){this.$axios({url:"/mia/logQuery",method:"post",data:{}}).then((t=>{t.data.success?this.QAHistoryList=t.data.data:this.$message.error("获取历史会话失败")})).catch((t=>{console.log(t),this.$message.error(t.message||"服务异常")}))}}},K=q,z=(0,m.A)(K,l,c,!1,null,"525d31ff",null),W=z.exports,F=e(9143),J=e.n(F);a["default"].config.productionTip=!1,a["default"].use(J()),a["default"].directive("scroll-to-bottom",{inserted:function(t){t.scrollTop=t.scrollHeight}}),new a["default"]({render:t=>t(W)}).$mount("#app")}},s={};function e(a){var i=s[a];if(void 0!==i)return i.exports;var n=s[a]={id:a,loaded:!1,exports:{}};return t[a].call(n.exports,n,n.exports,e),n.loaded=!0,n.exports}e.m=t,function(){e.amdO={}}(),function(){var t=[];e.O=function(s,a,i,n){if(!a){var r=1/0;for(u=0;u<t.length;u++){a=t[u][0],i=t[u][1],n=t[u][2];for(var o=!0,l=0;l<a.length;l++)(!1&n||r>=n)&&Object.keys(e.O).every((function(t){return e.O[t](a[l])}))?a.splice(l--,1):(o=!1,n<r&&(r=n));if(o){t.splice(u--,1);var c=i();void 0!==c&&(s=c)}}return s}n=n||0;for(var u=t.length;u>0&&t[u-1][2]>n;u--)t[u]=t[u-1];t[u]=[a,i,n]}}(),function(){e.n=function(t){var s=t&&t.__esModule?function(){return t["default"]}:function(){return t};return e.d(s,{a:s}),s}}(),function(){e.d=function(t,s){for(var a in s)e.o(s,a)&&!e.o(t,a)&&Object.defineProperty(t,a,{enumerable:!0,get:s[a]})}}(),function(){e.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(t){if("object"===typeof window)return window}}()}(),function(){e.o=function(t,s){return Object.prototype.hasOwnProperty.call(t,s)}}(),function(){e.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})}}(),function(){e.nmd=function(t){return t.paths=[],t.children||(t.children=[]),t}}(),function(){var t={524:0};e.O.j=function(s){return 0===t[s]};var s=function(s,a){var i,n,r=a[0],o=a[1],l=a[2],c=0;if(r.some((function(s){return 0!==t[s]}))){for(i in o)e.o(o,i)&&(e.m[i]=o[i]);if(l)var u=l(e)}for(s&&s(a);c<r.length;c++)n=r[c],e.o(t,n)&&t[n]&&t[n][0](),t[n]=0;return e.O(u)},a=self["webpackChunkchat_web"]=self["webpackChunkchat_web"]||[];a.forEach(s.bind(null,0)),a.push=s.bind(null,a.push.bind(a))}();var a=e.O(void 0,[504],(function(){return e(6445)}));a=e.O(a)})();
//# sourceMappingURL=app.77db5bf1.js.map