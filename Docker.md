# Docker安装和配置
# 环境
&emsp;&emsp;Ubuntu 20.04    

# Docker安装
1. 删除旧版本Docker
```cmd
sudo apt-get remove docker docker-engine docker.io containerd runc
```

2. 添加Docker官方GPG密钥
```cmd
curl -fsSL http://mirrors.aliyun.com/docker-ce/linux/ubuntu/gpg | sudo apt-key add -
```

3. 添加Docker软件源
```cmd
sudo add-apt-repository "deb [arch=amd64] http://mirrors.aliyun.com/docker-ce/linux/ubuntu $(lsb_release -cs) stable"
```

4. 安装Docker
```cmd
sudo apt-get install docker-ce docker-ce-cli containerd.io
```

5. 配置用户组(可选)
```cmd
sudo usermod -aG docker $USER
```

6. 启动Docker
```cmd
service docker start
```

7. 查看Docker版本
```cmd
sudo docker version
```

# 安装镜像
## 安装Mysql镜像

https://zhuanlan.zhihu.com/p/651148141
https://blog.csdn.net/BThinker/article/details/123471514
https://blog.csdn.net/u014282578/article/details/127920419