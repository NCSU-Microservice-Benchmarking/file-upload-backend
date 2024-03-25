# file-upload-backend

# 后端Springboot 打包Docker镜像

1. 修改application.yml中的数据库配置信息

![image](https://res.craft.do/user/full/ceb41fee-285b-9d66-0d89-c057dc8d84b4/doc/D68564AF-D8A4-4D6E-873A-C4CA4B6B9B4C/9CD83586-7E4A-4852-B8AE-E536279CBBB5_2/ZJR3ksH8XV2foe2y9eSETG1FyNKaQiiZmw7tn62G2Lsz/Image.png)

![image](https://res.craft.do/user/full/ceb41fee-285b-9d66-0d89-c057dc8d84b4/doc/D68564AF-D8A4-4D6E-873A-C4CA4B6B9B4C/583CF1B5-482F-4720-B8DE-082506C97C99_2/9qxBS1VLbrk0ReVXv35ixAlyxylONszaDww22TC6z2Yz/Image.png)


2. 使用Maven插件打jar包

![image](https://res.craft.do/user/full/ceb41fee-285b-9d66-0d89-c057dc8d84b4/doc/D68564AF-D8A4-4D6E-873A-C4CA4B6B9B4C/87D55EED-42D9-46BA-A2CB-5D2B9A83AB39_2/adXlXnj598veAXUJhBJPHB1IYTPXyekLFKEZsDaTUYoz/Image.png)

![image](https://res.craft.do/user/full/ceb41fee-285b-9d66-0d89-c057dc8d84b4/doc/D68564AF-D8A4-4D6E-873A-C4CA4B6B9B4C/1076FEAD-3448-412A-9EAD-F47F8CD0BAC5_2/f7HooygD9A5Dbko1CQq7MEgqXq2nyy0HqdyqL3ZxNIIz/Image.png)


3. 制作docker镜像：

```other
docker build -t springboot-file-uploader:v1.0 .
```

4. docker启动命令：  
   需要修改挂载本机文件夹路径，示例中是/tmp

```other
docker run -d -p 8080:8080  -v /tmp:/upload  --name springboot-file-uploader-container springboot-file-uploader:v1.0   
```


# 前端Vue打包镜像


```other
docker build -t vuejs-app .docker build -t vuejs-app .
```


```other
docker run -d -p 8081:8081 --name my-vuejs-app vuejs-app
```


# 本机MySQL建表：


```other
drop table if exists file;

CREATE TABLE `file` (

`id` bigint(20) NOT NULL AUTO_INCREMENT,

`name` varchar(100) NOT NULL COMMENT 'File name',

`md5` varchar(32) DEFAULT NULL COMMENT 'MD5',

`path` varchar(100) NOT NULL COMMENT 'File storage path',

`upload_time` datetime(3) NOT NULL COMMENT 'upload time',

`ext` varchar(255) DEFAULT NULL COMMENT 'extension',

PRIMARY KEY (`id`)

) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

```


v1.0存储的文件不带后缀，文件元数据存储在数据库中，需要访问数据库进行类型转换才可正常使用。

可以修改后端接口改为直接存储带有文件后缀的文件
