(function(t){function e(e){for(var n,s,c=e[0],i=e[1],l=e[2],u=0,v=[];u<c.length;u++)s=c[u],Object.prototype.hasOwnProperty.call(r,s)&&r[s]&&v.push(r[s][0]),r[s]=0;for(n in i)Object.prototype.hasOwnProperty.call(i,n)&&(t[n]=i[n]);d&&d(e);while(v.length)v.shift()();return o.push.apply(o,l||[]),a()}function a(){for(var t,e=0;e<o.length;e++){for(var a=o[e],n=!0,c=1;c<a.length;c++){var i=a[c];0!==r[i]&&(n=!1)}n&&(o.splice(e--,1),t=s(s.s=a[0]))}return t}var n={},r={app:0},o=[];function s(e){if(n[e])return n[e].exports;var a=n[e]={i:e,l:!1,exports:{}};return t[e].call(a.exports,a,a.exports,s),a.l=!0,a.exports}s.m=t,s.c=n,s.d=function(t,e,a){s.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:a})},s.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},s.t=function(t,e){if(1&e&&(t=s(t)),8&e)return t;if(4&e&&"object"===typeof t&&t&&t.__esModule)return t;var a=Object.create(null);if(s.r(a),Object.defineProperty(a,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var n in t)s.d(a,n,function(e){return t[e]}.bind(null,n));return a},s.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return s.d(e,"a",e),e},s.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},s.p="/";var c=window["webpackJsonp"]=window["webpackJsonp"]||[],i=c.push.bind(c);c.push=e,c=c.slice();for(var l=0;l<c.length;l++)e(c[l]);var d=i;o.push([0,"chunk-vendors"]),a()})({0:function(t,e,a){t.exports=a("56d7")},"56d7":function(t,e,a){"use strict";a.r(e);a("e260"),a("e6cf"),a("cca6"),a("a79d");var n=a("2b0e"),r=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-app",[a("v-content",[a("HelloWorld")],1)],1)},o=[],s=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("v-container",[n("v-layout",{attrs:{"text-center":"",wrap:""}},[n("v-flex",{attrs:{xs12:""}},[n("v-img",{staticClass:"my-3",attrs:{src:a("9b19"),contain:"",height:"200"}})],1),n("v-flex",{attrs:{"mb-4":""}},[n("h1",{staticClass:"display-2 font-weight-bold mb-3"},[t._v("Mobile Computing Course Attendance")]),n("p",{staticClass:"subheading font-weight-regular"},[t._v(" Please note that the system has a unique key for each student. "),n("br"),t._v("Cheating will be detected. So, be nice. :) ")])]),n("v-flex",{attrs:{"mb-5":"",xs12:""}},[n("v-layout",{attrs:{"justify-center":""}},[n("v-form",{ref:"form",attrs:{"lazy-validation":""},model:{value:t.valid,callback:function(e){t.valid=e},expression:"valid"}},[n("v-text-field",{attrs:{label:"Lecture Number",type:"number",required:""},model:{value:t.lectureNumber,callback:function(e){t.lectureNumber=e},expression:"lectureNumber"}}),n("v-btn",{staticClass:"mr-4",attrs:{color:"success"},on:{click:t.submit}},[t._v("Submit")]),n("v-btn",{staticClass:"mr-4",attrs:{color:"primary"},on:{click:t.viewAttendanceList}},[t._v("View attendance list")])],1)],1)],1),n("v-flex",{attrs:{"mb-5":"",xs12:""}},[n("v-layout",{attrs:{"justify-center":""}},[n("img",{attrs:{src:t.encodedQR}})])],1),n("v-dialog",{attrs:{fullscreen:"","hide-overlay":"",transition:"dialog-bottom-transition"},model:{value:t.dialog1,callback:function(e){t.dialog1=e},expression:"dialog1"}},[n("v-card",[n("v-toolbar",{attrs:{dark:"",color:"primary"}},[n("v-btn",{attrs:{icon:"",dark:""},on:{click:function(e){t.dialog1=!1}}},[n("v-icon",[t._v("mdi-close")])],1),n("v-toolbar-title",[t._v("Attendance List")])],1),n("v-list",{attrs:{"three-line":"",subheader:""}},[n("v-data-table",{attrs:{headers:t.headers,items:t.attendanceList,"items-per-page":10},scopedSlots:t._u([{key:"top",fn:function(){return[n("v-toolbar",{attrs:{flat:"",color:"white"}},[n("v-toolbar-title",[t._v("Lecture "+t._s(t.lectureNumber))]),n("v-divider",{staticClass:"mx-4",attrs:{inset:"",vertical:""}}),n("v-spacer"),n("v-dialog",{attrs:{"max-width":"500px"},scopedSlots:t._u([{key:"activator",fn:function(e){var a=e.on;return[n("v-btn",t._g({staticClass:"mb-2",attrs:{color:"primary",dark:""}},a),[t._v("Add Student")])]}}]),model:{value:t.dialog2,callback:function(e){t.dialog2=e},expression:"dialog2"}},[n("v-card",[n("v-card-title",[n("span",{staticClass:"headline"},[t._v("Add Student")])]),n("v-card-text",[n("v-container",[n("v-row",[n("v-col",{attrs:{cols:"12",sm:"6",md:"4"}},[n("v-text-field",{attrs:{label:"Student ID"},model:{value:t.editedItem.studentID,callback:function(e){t.$set(t.editedItem,"studentID",e)},expression:"editedItem.studentID"}})],1)],1)],1)],1),n("v-card-actions",[n("v-spacer"),n("v-btn",{attrs:{color:"blue darken-1",text:""},on:{click:t.close}},[t._v("Cancel")]),n("v-btn",{attrs:{color:"blue darken-1",text:""},on:{click:t.save}},[t._v("Save")])],1)],1)],1)],1)]},proxy:!0}])})],1)],1),n("v-snackbar",{model:{value:t.snackbar,callback:function(e){t.snackbar=e},expression:"snackbar"}},[t._v(" "+t._s(t.addStudentResponseMsg)+" "),n("v-btn",{attrs:{color:"pink",text:""},on:{click:function(e){t.snackbar=!1}}},[t._v("Close")])],1)],1),n("v-snackbar",{model:{value:t.atsnackbar,callback:function(e){t.atsnackbar=e},expression:"atsnackbar"}},[t._v(" "+t._s(t.viewAttendanceListResponseMsg)+" "),n("v-btn",{attrs:{color:"pink",text:""},on:{click:function(e){t.atsnackbar=!1}}},[t._v("Close")])],1)],1)],1)},c=[],i=a("bc3a"),l=a.n(i),d={name:"HelloWorld",data:function(){return{lectureNumber:"",valid:!0,encodedQR:"",dialog1:!1,dialog2:!1,attendanceList:[],headers:[{text:"ID",align:"left",sortable:!0,value:"studentID"}],editedItem:{studentID:""},defaultItem:{studentID:""},addStudentResponseMsg:"",viewAttendanceListResponseMsg:"",snackbar:!1,atsnackbar:!1}},methods:{submit:function(){var t=this,e={course:{name:"Mobile Computing",code:"CSE437"},lecture:{number:this.lectureNumber,date:Date.now()}};l.a.post("http://104.154.52.199:3000/qrcode",e).then((function(e){t.encodedQR=e.data})).catch((function(t){console.error(t)}))},viewAttendanceList:function(){var t,e=this;t="http://104.154.52.199:3000/attendance?week=".concat(this.lectureNumber),l.a.get(t).then((function(t){null==t.data?(e.viewAttendanceListResponseMsg="Please submit a lecture first to see attendance list",e.atsnackbar=!0):(e.dialog1=!0,e.attendanceList=t.data.students)})).catch((function(t){console.error(t)}))},close:function(){var t=this;this.dialog2=!1,setTimeout((function(){t.editedItem=Object.assign({},t.defaultItem)}),300)},save:function(){var t=this,e={course:{name:"Mobile Computing",code:"CSE437"},lecture:{number:this.lectureNumber,date:Date.now()},student:{id:this.editedItem.studentID}};l.a.post("http://104.154.52.199:3000/attendance",e).then((function(e){t.addStudentResponseMsg="Student ".concat(t.editedItem.studentID," is added successfully"),t.attendanceList=e.data.students,t.snackbar=!0,t.dialog1=!1})).catch((function(e){t.addStudentResponseMsg=e.response.data,t.snackbar=!0})),this.close()}}},u=d,v=a("2877"),b=a("6544"),f=a.n(b),p=a("8336"),m=a("b0af"),h=a("99d9"),g=a("62ad"),k=a("a523"),x=a("8fea"),y=a("169a"),_=a("ce7e"),w=a("0e8f"),C=a("4bd4"),V=a("132d"),S=a("adda"),I=a("a722"),D=a("8860"),j=a("0fd9"),L=a("2db4"),M=a("2fa4"),O=a("8654"),A=a("71d9"),R=a("2a7f"),T=Object(v["a"])(u,s,c,!1,null,null,null),N=T.exports;f()(T,{VBtn:p["a"],VCard:m["a"],VCardActions:h["a"],VCardText:h["b"],VCardTitle:h["c"],VCol:g["a"],VContainer:k["a"],VDataTable:x["a"],VDialog:y["a"],VDivider:_["a"],VFlex:w["a"],VForm:C["a"],VIcon:V["a"],VImg:S["a"],VLayout:I["a"],VList:D["a"],VRow:j["a"],VSnackbar:L["a"],VSpacer:M["a"],VTextField:O["a"],VToolbar:A["a"],VToolbarTitle:R["a"]});var P={name:"App",components:{HelloWorld:N},data:function(){return{}}},E=P,$=a("7496"),q=a("a75b"),F=Object(v["a"])(E,r,o,!1,null,null,null),H=F.exports;f()(F,{VApp:$["a"],VContent:q["a"]});var Q=a("f309");a("15f5");n["a"].use(Q["a"]);var W=new Q["a"]({icons:{iconfont:"fa"}});n["a"].config.productionTip=!1,new n["a"]({vuetify:W,render:function(t){return t(H)}}).$mount("#app")},"9b19":function(t,e,a){t.exports=a.p+"img/logo.63a7d78d.svg"}});
//# sourceMappingURL=app.ceec67ad.js.map