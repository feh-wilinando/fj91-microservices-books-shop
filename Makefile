
cookieFile ?=cookieFile

remove-cookie:
	@rm -f $(cookieFile)

get:
	@ curl -b $(cookieFile) -c $(cookieFile) http://localhost:8082/shopping-cart

put:
	@ curl -X PUT -b $(cookieFile) -c $(cookieFile) -H "Content-type: application/json" -H "Accept: application/json" -d '{"id":$(id)}' http://localhost:8082/shopping-cart

get-products:
ifdef id
	@ curl http://localhost:8082/products/$(id)
else
	@ curl http://localhost:8082/products
endif

package:
	@ echo "Packaging books-shop project"
	@ mvn clean package && rm -f books-shop.out

up: package remove-cookie
	@ echo "Start books-shop project"
	@ nohup java -jar target/*.jar &> books.out&

down: remove-cookie
	@ echo "Stop books-shop project"
	@ jps | grep "books-shop-.*\.jar" | cut -f 1 -d ' ' | xargs kill -9
