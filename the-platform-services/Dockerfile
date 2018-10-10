#python
FROM python:3.7.0
COPY src .
COPY requirements requirements
RUN pip install -r requirements/install.pip
CMD ["python", "/server.py"]
#-------------------------------------------------------------
