1. Run the command to pull the postgresql image to local system.
   docker pull postgres

2. To see the image got successfully pulled - Run the command
   docker images
   [This should show the postgres image]
   
3.Run the postgres database into the postgres container
  docker run -d -p 5432:5432 -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin@123 -e POSTGRES_DB=hackernews-db -v C:\postgres_container:/var/lib/postgresql/data --name=postgres_con postgres
 
4.Check if the container is running
  docker ps -a
  
5. Next is to create the Dockerfile inside the spring boot project:
   -Docker files is already created but hostname should not be localhost in application.yaml
   -To see what the actual host we need to check the IP address
   -Run the below command
    docker inspect postgres_con [Copy the IPAddress seen in the output and paste in place of localhost in application.yaml]
	Example url: jdbc:postgresql://<IPAddress>/hackernews-db
	
6. Build the image for hackernews-api
   docker build -t hackernews-api-img .

7. Check if the image is created using command
   docker images
   
8. Create a container out of hackernews-api-img
   docker run -d -p 8080:8080 --name=hackernews-api-con hackernews-api-img
   	
	
	
   