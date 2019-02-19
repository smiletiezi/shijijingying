/*新闻详情*/
var newsDetails = new Vue({
	el:'#news-details',
	data:{
		newsdetailslist:{},
		prevName:"没有上一篇了",
		nextName:"没有下一篇了",
		prevId:'',
		nextId:'',
		title:"",
		className:'',
		cfy:''
	},
	mounted:function(){
        this.showData();
        //需要执行的方法可以在mounted中进行触发，其获取的数据可以赋到data中后，可以放在前面进行渲染
    },
	methods:{
		showData:function(){
			var newid = global.getQueryString('id');
			this.cfy = global.getQueryString('classifyPermission');
			this.className = decodeURI(global.getQueryString('className'));
			var that = this;
			$.ajax({
				type:"post",
				url:global.data.baseUrl+'/jzmb/portalNews/selectNewDetails',
				data:{
					id:newid
				},
				success:function(data){
					that.title=data.DATA.title;
					if(data.CODE==1){
						that.newsdetailslist=data.DATA;
					}
				}
			});
			
			this.checkUpDown(newid,this.cfy);
		},
		checkUpDown:function (newid,cfy) {
			var that = this;
			$.ajax({
				type:"post",
				url:global.data.baseUrl+'/jzmb/portalNews/selectNewsByPermissionLastOrNext',
				data:{
					classifyPermission:cfy,
					type:'top',
					currentId:newid
				},
				success:function(data){console.log(data);
					if(data.CODE==1){
						if(data.newsLast.id){
							that.prevName=data.newsLast.title;
							that.prevId=data.newsLast.id;
						}
						if(data.newsNext.id){
							that.nextName=data.newsNext.title;
							that.nextId=data.newsNext.id;
						}
					}
				}
			});
			
		}
	}
});
