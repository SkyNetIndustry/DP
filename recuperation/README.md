RPIid - 245428838

docker run --name mysql -p3306:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=test -e MYSQL_USER=sa -e MYSQL_PASSWORD=password -d mysql
docker run --name my-own-phpmyadmin -d --link mysql:db -p 8081:80 phpmyadmin/phpmyadmin


rle s odchylkou 0.3 stupna na den?

3600 Json zaznamov na hodinu