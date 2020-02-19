# LunchAndLearnDocker
Project to expose in the lunch and learn

# How to create the image
docker build -t docker-example .

# How to run the container.
docker run --rm -d -p 8080:8080 docker-example 

# How to stop the container:
// Get the container id
docker ps
// stop the container 
docker stop <contained_id>
