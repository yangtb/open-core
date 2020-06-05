### 单元测试编写规范说明

- 测试类统一放到`src\test\java`目录底下，方便使用maven框架构建和测试

- 测试类的类名统一以`Test`作为前缀，后面加对应的接口名，如`TestUserFacade`

- 测试类统一继承`TestBase`类，开发人员可以忽略spring配置的加载和资源释放

- 测试方法统一以`test`作为前缀，后面加对应的测试方法名，如`testListUser`