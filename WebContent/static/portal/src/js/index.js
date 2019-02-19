/*
 * 团队精英
 */
var team = new Vue({
	el:"#gTeam",
	data:{
		lists:null,
		attachmentPath:''
		
	},
	mounted:function () {
		this.getData();
	},
	methods:{
		getData:function () {
			$.ajax({
				type:"post",
				url:global.data.baseUrl+"/jzmb/portalNews/selectNewsByPermission",
				data:{
					classifyPermission:'team_crew',
					type:'recommend',
					pageNum:'1',
					pageSize:'4'
				},
				success:function (data) {
					if(data.CODE == 1){
						team.lists = data.DATA;
						team.attachmentPath = data.attachmentPath;
					}else{
						console.log('团队精英>>>'+data.MES)
					}
				}
			});
		},
        enter: function(e){
			$(e.currentTarget).find('.u-name').fadeOut();
			$(e.currentTarget).find('.m-details').fadeIn();
        },
        leave: function(e){
			$(e.currentTarget).find('.u-name').fadeIn();
			$(e.currentTarget).find('.m-details').fadeOut();
        }
	}
})

/*
 * 精彩案例
 */
var cases = new Vue({
	el:'#gCases',
	data:{
		lists:null,
	},
	mounted:function () {
		this.getData();
	},
	methods:{
		getData:function () {
			$.ajax({
				type:"post",
				url:global.data.baseUrl+"/jzmb/portalNews/selectNewsByPermission",
				data:{
					classifyPermission:'classic_case',
					type:'recommend',
					pageNum:'1',
					pageSize:'1'
				},
				success:function (data) {
					if(data.CODE == 1){
						cases.lists = data.DATA;
					}else{
						console.log('精彩案例>>>'+data.MES)
					}
				}
			});
		}
	}
})


/*
 * 新闻法规 联系我们
 */
var news = new Vue({
	el:"#gNews",
	data:{
		newLists:null,
		contactLists:{}
		
	},
	mounted:function () {
		this.getNewsData();
		this.getContactData();
	},
	methods:{
		getNewsData:function () {
			$.ajax({
				type:"post",
				url:global.data.baseUrl+"/jzmb/portalNews/selectNewsByPermission",
				data:{
					classifyPermission:'news_laws',
					type:'recommend',
					pageNum:'1',
					pageSize:'4'
				},
				success:function (data) {
					if(data.CODE == 1){
						news.newLists = data.DATA;
					}else{
						console.log('新闻法规>>>'+data.MES)
					}
				}
			});
		},
		
		getContactData:function () {
			$.ajax({
				type:"post",
				url:global.data.baseUrl+"/jzmb/portalbasicinformation/selectbasicinformation",
				success:function (data) {
					if(data.CODE == 1){
						news.contactLists = data.ENTITY;
					}else{
						console.log('联系我们>>>'+data.MES)
					}
				}
			});
		}
		
	}
})

/*
 * 关于思宏
 */
var  gAboutus = new Vue({
	el:"#gAboutus",
	data:{
		isShow:false,
		list:[],
		attachmentPath:'',
		cur:0,//当前页数
		leng:0//所有页数
	},
	mounted:function () {
		
	},
	methods:{
		show:function () {
			this.isShow = true;
			this.getData();
		},
		close:function () {
			this.isShow = false;
		},
		getData:function () {
			var that = this;
			$.ajax({
				type:"post",
				url:global.data.baseUrl+"/jzmb/portalImgNews/getNewsListData",
				success:function (data) {
					if(data.CODE == 1){
						that.list = data.DATA;
						that.attachmentPath = data.attachmentPath;
						
						that.leng = data.DATA.length;
					}else{
						console.log('新闻法规>>>'+data.MES)
					}
				}
			});
		},
		prev:function () {
			this.cur--;
            if( this.cur<0 ){ this.cur=this.leng-1; }
            $(".m-layer .u-imgNewsLists li").eq( this.cur ).fadeIn().siblings().fadeOut();
            console.log(this.cur)
		},
		next:function () {
			this.cur++;
            if( this.cur>=this.leng ){ this.cur=0; }
            $(".m-layer .u-imgNewsLists li").eq( this.cur ).fadeIn().siblings().fadeOut();
            console.log(this.cur)
		}
	}
})
