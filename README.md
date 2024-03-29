
projenin kodu  geliştirilirken
Java 11,
Spring boot,
Rest Api,
mysql,
swagger, 
Mockito kullanılmıştır.
apache solr entegrasyonu yapıldı ancak bilgisayara kurulumunda hata alıyordum. mysql  ile devam ettim.

tables;

CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `name` varchar(1000) NOT NULL,
  `sur_name` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) 


CREATE TABLE `restaurant` (
  `id` bigint NOT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `name` varchar(1000) NOT NULL,
  `score` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) 

-- restaurantmanagement.user_comment definition

CREATE TABLE `user_comment` (
  `id` bigint NOT NULL,
  `score` int NOT NULL,
  `text` varchar(255) NOT NULL,
  `restaurant_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4rdbkh2bi5ypt191jfb3kh0k5` (`restaurant_id`),
  KEY `FKornrskknlmumgdhlohpbcvrw5` (`user_id`),
  CONSTRAINT `FK4rdbkh2bi5ypt191jfb3kh0k5` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`),
  CONSTRAINT `FKornrskknlmumgdhlohpbcvrw5` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) 

------------------------------------------------------
user dataları;
INSERT INTO restaurantmanagement.`user`
(id, latitude, longitude, name, sur_name)
VALUES(6, 40.7128, -74.006, 'huseyin', 'yakut');
INSERT INTO restaurantmanagement.`user`
(id, latitude, longitude, name, sur_name)
VALUES(7, 40.7143, -74.0062, 'mehmet', 'yakut');

restaurant dataları;

INSERT INTO restaurantmanagement.restaurant
(id, latitude, longitude, name, score)
VALUES(6, 40.7128, -74.006, 'Restaurant1', 4.5);
INSERT INTO restaurantmanagement.restaurant
(id, latitude, longitude, name, score)
VALUES(7, 40.7143, -74.0062, 'Restaurant2', 3.8);
INSERT INTO restaurantmanagement.restaurant
(id, latitude, longitude, name, score)
VALUES(8, 40.7151, -74.0059, 'Restaurant3', 4.2);
INSERT INTO restaurantmanagement.restaurant
(id, latitude, longitude, name, score)
VALUES(9, 40.716, -74.0063, 'Restaurant4', 4.0);
INSERT INTO restaurantmanagement.restaurant
(id, latitude, longitude, name, score)
VALUES(10, 40.719, -74.0055, 'Restaurant5', 4.8);


user-comment dataalrı


INSERT INTO restaurantmanagement.user_comment
(id, score, `text`, restaurant_id, user_id)
VALUES(3, 2, 'yemekler soğuktu', 6, 6);
INSERT INTO restaurantmanagement.user_comment
(id, score, `text`, restaurant_id, user_id)
VALUES(4, 2, 'yemeklerisevmedim', 8, 6);
INSERT INTO restaurantmanagement.user_comment
(id, score, `text`, restaurant_id, user_id)
VALUES(5, 5, 'yemekler şahane', 9, 7);


swagger  api dokumanı;


{"swagger":"2.0","info":{"description":"Api Documentation","version":"1.0","title":"Api Documentation","termsOfService":"urn:tos","contact":{},"license":{"name":"Apache 2.0","url":"http://www.apache.org/licenses/LICENSE-2.0"}},"host":"localhost:8080","basePath":"/","tags":[{"name":"basic-error-controller","description":"Basic Error Controller"},{"name":"comment-controller","description":"Comment Controller"},{"name":"operation-handler","description":"Operation Handler"},{"name":"restaurant-controller","description":"Restaurant Controller"},{"name":"user-controller","description":"User Controller"},{"name":"web-mvc-links-handler","description":"Web Mvc Links Handler"}],"paths":{"/N11/UserComments/comments":{"post":{"tags":["comment-controller"],"summary":"/N11/UserComments/comments","operationId":"createCommentUsingPOST","consumes":["application/json"],"produces":["*/*"],"parameters":[{"in":"body","name":"commentDto","description":"commentDto","required":true,"schema":{"$ref":"#/definitions/CommentCreateRequestDto"}}],"responses":{"200":{"description":"OK","schema":{"type":"object"}},"201":{"description":"Created"},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"},"404":{"description":"Not Found"}},"deprecated":false}},"/N11/UserComments/comments/{id}":{"put":{"tags":["comment-controller"],"summary":"/N11/UserComments/comments","operationId":"updateCommentUsingPUT","consumes":["application/json"],"produces":["*/*"],"parameters":[{"in":"body","name":"commentUpdateRequest","description":"commentUpdateRequest","required":true,"schema":{"$ref":"#/definitions/CommentUpdateRequest"}},{"name":"id","in":"path","description":"id","required":true,"type":"integer","format":"int64"}],"responses":{"200":{"description":"OK","schema":{"type":"string"}},"201":{"description":"Created"},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"},"404":{"description":"Not Found"}},"deprecated":false},"delete":{"tags":["comment-controller"],"summary":"/N11/UserComments/comments","operationId":"deleteCommentUsingDELETE","produces":["*/*"],"parameters":[{"name":"id","in":"path","description":"id","required":true,"type":"integer","format":"int64"}],"responses":{"200":{"description":"OK","schema":{"type":"string"}},"204":{"description":"No Content"},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"}},"deprecated":false}},"/N11/restaurants":{"get":{"tags":["restaurant-controller"],"summary":"/N11/restaurants","operationId":"getAllRestaurantsUsingGET","produces":["*/*"],"responses":{"200":{"description":"OK","schema":{"type":"array","items":{"$ref":"#/definitions/Restaurant"}}},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"},"404":{"description":"Not Found"}},"deprecated":false},"post":{"tags":["restaurant-controller"],"summary":"/N11/restaurants","operationId":"createRestaurantUsingPOST","consumes":["application/json"],"produces":["*/*"],"parameters":[{"in":"body","name":"restaurantDto","description":"restaurantDto","required":true,"schema":{"$ref":"#/definitions/RestaurantCreateRequestDto"}}],"responses":{"200":{"description":"OK","schema":{"type":"object"}},"201":{"description":"Created"},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"},"404":{"description":"Not Found"}},"deprecated":false}},"/N11/restaurants/recommendations":{"get":{"tags":["restaurant-controller"],"summary":"/N11/restaurants/recommendations","operationId":"getRecommendationsUsingGET","produces":["*/*"],"parameters":[{"name":"userLatitude","in":"query","description":"userLatitude","required":true,"type":"number","format":"double"},{"name":"userLongitude","in":"query","description":"userLongitude","required":true,"type":"number","format":"double"}],"responses":{"200":{"description":"OK","schema":{"type":"array","items":{"$ref":"#/definitions/Restaurant"}}},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"},"404":{"description":"Not Found"}},"deprecated":false}},"/N11/restaurants/{id}":{"put":{"tags":["restaurant-controller"],"summary":"/N11/restaurants","operationId":"updateRestaurantUsingPUT","consumes":["application/json"],"produces":["*/*"],"parameters":[{"name":"id","in":"path","description":"id","required":true,"type":"integer","format":"int64"},{"in":"body","name":"restaurantUpdateRequest","description":"restaurantUpdateRequest","required":true,"schema":{"$ref":"#/definitions/RestaurantUpdateRequest"}}],"responses":{"200":{"description":"OK","schema":{"$ref":"#/definitions/Optional«Restaurant»"}},"201":{"description":"Created"},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"},"404":{"description":"Not Found"}},"deprecated":false}},"/N11/restaurants/{restaurantId}":{"delete":{"tags":["restaurant-controller"],"summary":"/N11/restaurants","operationId":"deleteRestaurantUsingDELETE","produces":["*/*"],"parameters":[{"name":"restaurantId","in":"path","description":"restaurantId","required":true,"type":"integer","format":"int64"}],"responses":{"200":{"description":"OK","schema":{"type":"object"}},"204":{"description":"No Content"},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"}},"deprecated":false}},"/N11/user":{"post":{"tags":["user-controller"],"summary":"/N11/user","operationId":"createUserUsingPOST","consumes":["application/json"],"produces":["*/*"],"parameters":[{"in":"body","name":"userDto","description":"userDto","required":true,"schema":{"$ref":"#/definitions/UserCreateRequestDto"}}],"responses":{"200":{"description":"OK","schema":{"$ref":"#/definitions/User"}},"201":{"description":"Created"},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"},"404":{"description":"Not Found"}},"deprecated":false}},"/N11/user/{id}":{"put":{"tags":["user-controller"],"summary":"/N11/user/{id}","operationId":"updateUserUsingPUT","consumes":["application/json"],"produces":["*/*"],"parameters":[{"name":"id","in":"path","description":"id","required":true,"type":"integer","format":"int64"},{"in":"body","name":"userUpdateRequest","description":"userUpdateRequest","required":true,"schema":{"$ref":"#/definitions/UserUpdateRequest"}}],"responses":{"200":{"description":"OK","schema":{"$ref":"#/definitions/User"}},"201":{"description":"Created"},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"},"404":{"description":"Not Found"}},"deprecated":false},"delete":{"tags":["user-controller"],"summary":"/N11/user/{id}","operationId":"deleteUserUsingDELETE","produces":["*/*"],"parameters":[{"name":"id","in":"path","description":"id","required":true,"type":"integer","format":"int64"}],"responses":{"200":{"description":"OK"},"204":{"description":"No Content"},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"}},"deprecated":false}},"/actuator":{"get":{"tags":["web-mvc-links-handler"],"summary":"links","operationId":"linksUsingGET","produces":["application/vnd.spring-boot.actuator.v3+json","application/json","application/vnd.spring-boot.actuator.v2+json"],"responses":{"200":{"description":"OK","schema":{"type":"object","additionalProperties":{"type":"object","additionalProperties":{"$ref":"#/definitions/Link"}}}},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"},"404":{"description":"Not Found"}},"deprecated":false}},"/actuator/health":{"get":{"tags":["operation-handler"],"summary":"handle","operationId":"handleUsingGET_1","produces":["application/vnd.spring-boot.actuator.v3+json","application/json","application/vnd.spring-boot.actuator.v2+json"],"parameters":[{"in":"body","name":"body","description":"body","required":false,"schema":{"type":"object","additionalProperties":{"type":"string"}}}],"responses":{"200":{"description":"OK","schema":{"type":"object"}},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"},"404":{"description":"Not Found"}},"deprecated":false}},"/actuator/health/**":{"get":{"tags":["operation-handler"],"summary":"handle","operationId":"handleUsingGET","produces":["application/vnd.spring-boot.actuator.v3+json","application/json","application/vnd.spring-boot.actuator.v2+json"],"parameters":[{"in":"body","name":"body","description":"body","required":false,"schema":{"type":"object","additionalProperties":{"type":"string"}}}],"responses":{"200":{"description":"OK","schema":{"type":"object"}},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"},"404":{"description":"Not Found"}},"deprecated":false}},"/actuator/info":{"get":{"tags":["operation-handler"],"summary":"handle","operationId":"handleUsingGET_2","produces":["application/vnd.spring-boot.actuator.v3+json","application/json","application/vnd.spring-boot.actuator.v2+json"],"parameters":[{"in":"body","name":"body","description":"body","required":false,"schema":{"type":"object","additionalProperties":{"type":"string"}}}],"responses":{"200":{"description":"OK","schema":{"type":"object"}},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"},"404":{"description":"Not Found"}},"deprecated":false}},"/error":{"get":{"tags":["basic-error-controller"],"summary":"error","operationId":"errorUsingGET","produces":["*/*"],"responses":{"200":{"description":"OK","schema":{"type":"object","additionalProperties":{"type":"object"}}},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"},"404":{"description":"Not Found"}},"deprecated":false},"head":{"tags":["basic-error-controller"],"summary":"error","operationId":"errorUsingHEAD","consumes":["application/json"],"produces":["*/*"],"responses":{"200":{"description":"OK","schema":{"type":"object","additionalProperties":{"type":"object"}}},"204":{"description":"No Content"},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"}},"deprecated":false},"post":{"tags":["basic-error-controller"],"summary":"error","operationId":"errorUsingPOST","consumes":["application/json"],"produces":["*/*"],"responses":{"200":{"description":"OK","schema":{"type":"object","additionalProperties":{"type":"object"}}},"201":{"description":"Created"},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"},"404":{"description":"Not Found"}},"deprecated":false},"put":{"tags":["basic-error-controller"],"summary":"error","operationId":"errorUsingPUT","consumes":["application/json"],"produces":["*/*"],"responses":{"200":{"description":"OK","schema":{"type":"object","additionalProperties":{"type":"object"}}},"201":{"description":"Created"},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"},"404":{"description":"Not Found"}},"deprecated":false},"delete":{"tags":["basic-error-controller"],"summary":"error","operationId":"errorUsingDELETE","produces":["*/*"],"responses":{"200":{"description":"OK","schema":{"type":"object","additionalProperties":{"type":"object"}}},"204":{"description":"No Content"},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"}},"deprecated":false},"patch":{"tags":["basic-error-controller"],"summary":"error","operationId":"errorUsingPATCH","consumes":["application/json"],"produces":["*/*"],"responses":{"200":{"description":"OK","schema":{"type":"object","additionalProperties":{"type":"object"}}},"204":{"description":"No Content"},"401":{"description":"Unauthorized"},"403":{"description":"Forbidden"}},"deprecated":false}}},"definitions":{"CommentCreateRequestDto":{"type":"object","properties":{"restaurantName":{"type":"string"},"score":{"type":"integer","format":"int32"},"text":{"type":"string"},"userName":{"type":"string"}},"title":"CommentCreateRequestDto"},"CommentUpdateRequest":{"type":"object","properties":{"score":{"type":"integer","format":"int32"},"text":{"type":"string"}},"title":"CommentUpdateRequest"},"Link":{"type":"object","properties":{"href":{"type":"string"},"templated":{"type":"boolean"}},"title":"Link"},"Map«string,Link»":{"type":"object","title":"Map«string,Link»","additionalProperties":{"$ref":"#/definitions/Link"}},"ModelAndView":{"type":"object","properties":{"empty":{"type":"boolean"},"model":{"type":"object"},"modelMap":{"type":"object","additionalProperties":{"type":"object"}},"reference":{"type":"boolean"},"status":{"type":"string","enum":["100 CONTINUE","101 SWITCHING_PROTOCOLS","102 PROCESSING","103 CHECKPOINT","200 OK","201 CREATED","202 ACCEPTED","203 NON_AUTHORITATIVE_INFORMATION","204 NO_CONTENT","205 RESET_CONTENT","206 PARTIAL_CONTENT","207 MULTI_STATUS","208 ALREADY_REPORTED","226 IM_USED","300 MULTIPLE_CHOICES","301 MOVED_PERMANENTLY","302 FOUND","302 MOVED_TEMPORARILY","303 SEE_OTHER","304 NOT_MODIFIED","305 USE_PROXY","307 TEMPORARY_REDIRECT","308 PERMANENT_REDIRECT","400 BAD_REQUEST","401 UNAUTHORIZED","402 PAYMENT_REQUIRED","403 FORBIDDEN","404 NOT_FOUND","405 METHOD_NOT_ALLOWED","406 NOT_ACCEPTABLE","407 PROXY_AUTHENTICATION_REQUIRED","408 REQUEST_TIMEOUT","409 CONFLICT","410 GONE","411 LENGTH_REQUIRED","412 PRECONDITION_FAILED","413 PAYLOAD_TOO_LARGE","413 REQUEST_ENTITY_TOO_LARGE","414 URI_TOO_LONG","414 REQUEST_URI_TOO_LONG","415 UNSUPPORTED_MEDIA_TYPE","416 REQUESTED_RANGE_NOT_SATISFIABLE","417 EXPECTATION_FAILED","418 I_AM_A_TEAPOT","419 INSUFFICIENT_SPACE_ON_RESOURCE","420 METHOD_FAILURE","421 DESTINATION_LOCKED","422 UNPROCESSABLE_ENTITY","423 LOCKED","424 FAILED_DEPENDENCY","425 TOO_EARLY","426 UPGRADE_REQUIRED","428 PRECONDITION_REQUIRED","429 TOO_MANY_REQUESTS","431 REQUEST_HEADER_FIELDS_TOO_LARGE","451 UNAVAILABLE_FOR_LEGAL_REASONS","500 INTERNAL_SERVER_ERROR","501 NOT_IMPLEMENTED","502 BAD_GATEWAY","503 SERVICE_UNAVAILABLE","504 GATEWAY_TIMEOUT","505 HTTP_VERSION_NOT_SUPPORTED","506 VARIANT_ALSO_NEGOTIATES","507 INSUFFICIENT_STORAGE","508 LOOP_DETECTED","509 BANDWIDTH_LIMIT_EXCEEDED","510 NOT_EXTENDED","511 NETWORK_AUTHENTICATION_REQUIRED"]},"view":{"$ref":"#/definitions/View"},"viewName":{"type":"string"}},"title":"ModelAndView"},"Optional«Restaurant»":{"type":"object","properties":{"empty":{"type":"boolean"},"present":{"type":"boolean"}},"title":"Optional«Restaurant»"},"Restaurant":{"type":"object","properties":{"id":{"type":"integer","format":"int64"},"latitude":{"type":"number","format":"double"},"longitude":{"type":"number","format":"double"},"name":{"type":"string"},"score":{"type":"number","format":"double"}},"title":"Restaurant"},"RestaurantCreateRequestDto":{"type":"object","properties":{"latitude":{"type":"number","format":"double"},"longitude":{"type":"number","format":"double"},"name":{"type":"string"},"rating":{"type":"number","format":"double"}},"title":"RestaurantCreateRequestDto"},"RestaurantUpdateRequest":{"type":"object","properties":{"latitude":{"type":"number","format":"double"},"longitude":{"type":"number","format":"double"},"name":{"type":"string"}},"title":"RestaurantUpdateRequest"},"User":{"type":"object","properties":{"id":{"type":"integer","format":"int64"},"latitude":{"type":"number","format":"double"},"longitude":{"type":"number","format":"double"},"name":{"type":"string"},"surName":{"type":"string"}},"title":"User"},"UserCreateRequestDto":{"type":"object","properties":{"latitude":{"type":"number","format":"double"},"longitude":{"type":"number","format":"double"},"name":{"type":"string"},"surName":{"type":"string"}},"title":"UserCreateRequestDto"},"UserUpdateRequest":{"type":"object","properties":{"latitude":{"type":"number","format":"double"},"longitude":{"type":"number","format":"double"},"name":{"type":"string"},"surname":{"type":"string"}},"title":"UserUpdateRequest"},"View":{"type":"object","properties":{"contentType":{"type":"string"}},"title":"View"}}}
