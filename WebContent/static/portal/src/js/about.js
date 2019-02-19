/*思宏简介*/
var vm = new Vue({
	el:'#about-cont-title',
	data:{
		content:null,
		title:null,
	},
	mounted:function(){
        this.showData();
        //需要执行的方法可以在mounted中进行触发，其获取的数据可以赋到data中后，可以放在前面进行渲染
    },
	methods:{
		showData:function(){
			var that = this;
			$.ajax({
			type:"post",
			url:global.data.baseUrl+'/jzmb/portalNews/selectEssayNews',
			data:{
				classifyPermission:'detail_introduction'
			},
			success:function(data){
				console.log(data);
				that.content=data.DATA.content;
				that.title=data.DATA.title;
			}
		});
		}
	}
})

/*联系我们*/
var contact = new Vue({
	el:'#contact',
	data:{
		fixedPhone:null,
		linkman:null,
		email:null,
		phone:null,
		address:null,
		lat:null,
		lng:null
	},
	mounted:function(){
		this.contactData();
		
	},
	methods:{
		contactData:function(){
			var that = this;
			$.ajax({
				type:"post",
				url:global.data.baseUrl+'/jzmb/portalbasicinformation/selectbasicinformation',
				data:{
					entity:''
				},
				success:function(data){
					console.log(data);
					that.fixedPhone=data.ENTITY.fixedPhone;
					that.linkman=data.ENTITY.linkman;
					that.email=data.ENTITY.email;
					that.phone=data.ENTITY.phone;
					that.address=data.ENTITY.address;
					that.lat=data.ENTITY.latitude;
					that.lng=data.ENTITY.longitude;
					that.showMap(that.lat,that.lng);
				}
			});
		},
		showMap:function (lat,lng) {
			console.log(lat,lng)
			var map = new BMap.Map("mymap");
			// 创建地图实例  
			var point = new BMap.Point(lng,lat);
			// 创建点坐标  
			map.centerAndZoom(point, 15);
			// 初始化地图，设置中心点坐标和地图级别 
			var marker = new BMap.Marker(point);
			// 创建标注
			map.addOverlay(marker);
			// 将标注添加到地图中
		}
	}
})

