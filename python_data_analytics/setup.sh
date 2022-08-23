cd python_data_wrangling
docker run -d --name jrvs-jupyter -p 8888:8888 -e JUPYTER_ENABLE_LAB=no -v "$PWD":/home/jovyan jupyter/datascience-notebook:python-3.8.5
docker network connect jarvis-net jrvs-jupyter
docker logs jrvs-jupyter