====================================================================================
1. 项目目录结构

open-core		核心层相关工程结构
		|
		|——— open-core-common		公共模块，供open-core-service和open-core-web使用
		|——— open-core-dal		    数据访问或持久化层，如mysql访问及持久化，供open-core-service使用
		|——— open-core-facade		对外暴露服务接口，供open-core-service使用
		|——— open-core-integration  集成外部接口服务，供open-core-web使用
		|——— open-core-model		一些数据模型及接口模型，包括注解、枚举、常量、入参、出参、实体、接口模型等，供open-core-service和open-core-web使用
		|——— open-core-service	    统一开放网关服务支撑服务，为服务open-core-web提供数据、逻辑等服务支撑，可负载。【注意】仅向open-core-web提供服务支撑
		|——— open-core-web		    提供统一标准rest接口服务

====================================================================================
2. open-core-service依赖关系

			|——— open-core-dal
			|				|——— open-core-model
			|
open-core-service
			|——— open-core-common
			|——— open-core-facade

====================================================================================
3. open-core-web依赖关系

			|——— open-core-integration
			|				|——— open-core-model
			|				|——— open-core-sdk
			|
open-core-web
			|
			|——— open-core-model
			|——— open-core-common

====================================================================================

