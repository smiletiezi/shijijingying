/*新闻资讯*/
var newslist = new Vue({
	el:'#list',
	data:{
		lists:[],
		recoms:[],//相关推荐
		numList:[],
        cur: 1,//当前页码,
        pageCount:0,//总页数
        className:""
	},
	mounted:function(){
        this.newslistData();
   		this.recommendData();
    },
    methods:{
    	newslistData:function(){
    		var that = this;
    		$.ajax({
				type:"post",
				url:global.data.baseUrl+'/jzmb/portalNews/selectNewsByPermission',
				data:{
					classifyPermission:'lawyer_essay',
					type:'top',
					pageNum:'1',
					pageSize:'4'
				},
				success:function(data){
					that.lists=data.DATA;
					that.className=data.DATA[0].classifyName;
					that.getPageNum(data.page);
				}
			});
    	},
    	recommendData:function(){
    		var that = this;
    		$.ajax({
				type:"post",
				url:global.data.baseUrl+'/jzmb/portalNews/selectNewsByPermission',
				data:{
					classifyPermission:'lawyer_essay',
					type:'recommend',
					pageNum:'1',
					pageSize:'3'
				},
				success:function(data){
					that.recoms=data.DATA;
				}
			});
    	},
    	//分页
    	getPageNum:function (obj) {
			this.pageCount = obj.pageCount;
			for (var i=0;i<this.pageCount;i++) {
				this.numList[i]=i+1;
			}
		},
		checkPage:function (e) {
    		var that = this;
			var $tar = $(e.currentTarget);
			that.cur = $(e.currentTarget).text();
    		
    		$tar.addClass("active").siblings("a").removeClass("active");
    		
    		$.ajax({
				type:"post",
				url:global.data.baseUrl+'/jzmb/portalNews/selectNewsByPermission',
				data:{
					classifyPermission:'lawyer_essay',
					type:'top',
					pageNum:that.cur,
					pageSize:'4'
				},
				success:function(data){
					that.lists=data.DATA;
					that.getPageNum(data.page);
				}
			});
		},
		prevPage:function () {
    		var that = this;
			that.cur--;
			$.ajax({
				type:"post",
				url:global.data.baseUrl+'/jzmb/portalNews/selectNewsByPermission',
				data:{
					classifyPermission:'lawyer_essay',
					type:'top',
					pageNum:that.cur,
					pageSize:'4'
				},
				success:function(data){
					that.lists=data.DATA;
					that.getPageNum(data.page);
				}
			});
		},
		nextPage:function () {
    		var that = this;
			that.cur++;
			$.ajax({
				type:"post",
				url:global.data.baseUrl+'/jzmb/portalNews/selectNewsByPermission',
				data:{
					classifyPermission:'lawyer_essay',
					type:'top',
					pageNum:that.cur,
					pageSize:'4'
				},
				success:function(data){
					that.lists=data.DATA;
					that.getPageNum(data.page);
				}
			});
		}
    }
})






