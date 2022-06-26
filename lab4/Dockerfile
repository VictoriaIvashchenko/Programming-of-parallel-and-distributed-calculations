FROM python:3
ENV PYTHONUNBUFFERED 1

WORKDIR /lab4
COPY requirements.txt .
RUN pip install -r requirements.txt

COPY . .

RUN python manage.py migrate

ENV PORT=8888
EXPOSE $PORT
ENTRYPOINT ["python", "manage.py", "runserver", "0.0.0.0:$PORT"]
