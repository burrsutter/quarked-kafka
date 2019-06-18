FROM registry.access.redhat.com/ubi7-dev-preview/ubi-minimal
WORKDIR /work/
COPY target/qkafkaconsumer-1.0.0-runner /work/qkafkaconsumer-1.0.0-runner
RUN chmod 775 /work
EXPOSE 8080
ENTRYPOINT [ "./qkafkaconsumer-1.0.0-runner",  "-Xmx8m", "-Xmn8m", "-Xms8m"]