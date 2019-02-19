//全局对象，谨慎修改
var global={
	data:{
		baseUrl:'http://192.168.2.157:8080'
	},
	getQueryString:function (name) {
	    var result = window.location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
	    if (result == null || result.length < 1) {
	        return "";
	    }
	    return result[1];
	}
};



//公共组件

/*
 *头部导航
 */

var hd = new Vue({
	el: '#g-hd',
	data: {
		lists:null,
		isHover:false
	},
	mounted:function () {
		this.getData();
	},
	methods:{
	  	getData:function(){
	  		var that = this;
		  	$.ajax({
		  		url:global.data.baseUrl+'/jzmb/portalNews/selectColumn',
		  		type:'post',
		  		success:function (data) {
		  			that.lists = data.DATA;
		  		}
		  	});
	  	},
        enter: function(e){
            e.currentTarget.className = 'show'
        },
        leave: function(e){
            e.currentTarget.className = ''
        }
	}
})

/*
 *底部
 */

var footer = new Vue({
	el: '#gFooter',
	data: {
		lists:null,
		contactLists:{}
		
	},
	mounted:function () {
		this.getLinkData();
		this.getContactData();
	},
	methods:{
		
	  	getLinkData:function(){
	  		var that = this;
		  	$.ajax({
		  		url:global.data.baseUrl+'/jzmb/frontfriendlyLink/selectfronFriendlyLink',
		  		type:'post',
		  		success:function (data) {
		  			that.lists = data.data;
		  		}
		  	});
	  	},
		getContactData:function () {
			$.ajax({
				type:"post",
				url:global.data.baseUrl+"/jzmb/portalbasicinformation/selectbasicinformation",
				success:function (data) {
					if(data.CODE == 1){
						footer.contactLists = data.ENTITY;
					}else{
						console.log('联系我们>>>'+data.MES)
					}
				}
			});
		}
	  	
	}
})
