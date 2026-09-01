cmake_minimum_required(VERSION 3.16)

project(test-app LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

add_executable(app
  src/main.cpp
  src/handlers.cpp
)

target_include_directories(app PRIVATE include src)

find_package(Threads REQUIRED)
target_link_libraries(app PRIVATE Threads::Threads)
