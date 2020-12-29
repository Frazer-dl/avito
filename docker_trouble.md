    1. Возникает ошибка при попытке отправки http запроса postman -> spring-app -> redis 
    на узле spring-app -> redis. При этом сервер redis запущен и к нему можно подключиться 
    клиентом. В свою очередь команды от postman -> spring-app -> cache проходят успешно, 
    данные создаются и выгружаются.
___
    kgenov@kgenov-virtual-machine:~$ docker network inspect spring

[
{
"Name": "spring",
"Id": "2651cf350a2939e5b1c8e3b2756ff4df11d61e5a45be4551d9cee0d600c37603",
"Created": "2020-12-28T23:02:04.036366269+03:00",
"Scope": "local",
"Driver": "bridge",
"EnableIPv6": false,
"IPAM": {
"Driver": "default",
"Options": {},
"Config": [
{
"Subnet": "172.20.0.0/16",
"Gateway": "172.20.0.1"
}
]
},
"Internal": false,
"Attachable": false,
"Ingress": false,
"ConfigFrom": {
"Network": ""
},
"ConfigOnly": false,
"Containers": {
"60673b7344a333c146af4c494de0707ecaa9c19dbb8191fc2dd42bda9eee7fd6": {
"Name": "avito_app_1",
"EndpointID": "f4fd3efc112b574aa3f81037f7280a8fe6417013326e31089ccd893a188cffc4",
"MacAddress": "02:42:ac:14:00:03",
"IPv4Address": "172.20.0.3/16",
"IPv6Address": ""
},
"8a866b24ebdbcfdfa20201bf3e6769fe3eddc60ea478707fbb0734330523ce0a": {
"Name": "avito_redis_1",
"EndpointID": "ed9b324035f09790fc48e72ca56d1b15f77f6173bc2213c8c7a46262cfbcbdbc",
"MacAddress": "02:42:ac:14:00:02",
"IPv4Address": "172.20.0.2/16",
"IPv6Address": ""
}
},
"Options": {},
"Labels": {}
}
]



    kgenov@kgenov-virtual-machine:~$ docker ps

CONTAINER ID   IMAGE       COMMAND                  CREATED          STATUS          PORTS                    NAMES
60673b7344a3   avito_app   "/bin/sh -c 'exec ja…"   13 minutes ago   Up 12 minutes   0.0.0.0:8082->8082/tcp   avito_app_1
8a866b24ebdb   redis       "docker-entrypoint.s…"   13 minutes ago   Up 13 minutes   0.0.0.0:6379->6379/tcp   avito_redis_1


    kgenov@kgenov-virtual-machine:~$ docker ps

CONTAINER ID   IMAGE       COMMAND                  CREATED          STATUS         PORTS                    NAMES
60673b7344a3   avito_app   "/bin/sh -c 'exec ja…"   41 minutes ago   Up 5 minutes   0.0.0.0:8082->8082/tcp   avito_app_1

    kgenov@kgenov-virtual-machine:~$ sudo docker run -d -p 127.0.0.1:6379:6379 --restart unless-stopped -v /etc/redis/:/data redis redis-server /data

5fa0d219d8e27e369194a39c91886686ea9e6f48725bcbb5e0d06e77c495a561

    kgenov@kgenov-virtual-machine:~$ docker ps

CONTAINER ID   IMAGE       COMMAND                  CREATED          STATUS         PORTS                      NAMES
5fa0d219d8e2   redis       "docker-entrypoint.s…"   8 seconds ago    Up 7 seconds   127.0.0.1:6379->6379/tcp   compassionate_ptolemy
60673b7344a3   avito_app   "/bin/sh -c 'exec ja…"   42 minutes ago   Up 6 minutes   0.0.0.0:8082->8082/tcp     avito_app_1

    kgenov@kgenov-virtual-machine:~$ docker network ls
2651cf350a29   spring          bridge    local
___
    2. Вылетает ошибка при создании папки внутри контейнера, в свою очередь виртуальный контейнер 
    в докере создан и прописан в *.yml
