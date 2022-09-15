FROM gradle:jdk17
WORKDIR /home/gradle
CMD ["gradle", "--stacktrace", "run --args='prdWithoutServer'"]
