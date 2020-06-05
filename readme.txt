====================================================================================
1. 项目目录结构

open-core		核心层相关工程结构
		|
		|——— open-core-common		公共模块，供open-core-service使用
		|——— open-core-dal		    数据访问或持久化层，如mysql访问及持久化，供open-core-service使用
		|——— open-core-facade		对外暴露服务接口，供open-core-service使用
		|——— open-core-integration  集成外部接口服务
		|——— open-core-model		一些数据模型及接口模型，包括注解、枚举、常量、入参、出参、实体、接口模型等，供open-core-service使用
		|——— open-core-service
		|——— open-core-service-mb   mb示例service，不同项目模块独立出来

====================================================================================
2. open-core-service依赖关系

            |——— open-care-core     公用工具类、枚举等，供所有项目使用
			|——— open-core-dal
			|				|——— open-core-model
			|
open-core-service
			|——— open-core-common
			|——— open-core-facade
			|——— open-core-integration
			|——— open-core-service-mb
			|——— open-core-service-**
====================================================================================


