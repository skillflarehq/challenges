cmake_minimum_required(VERSION 3.16)

project(test-app LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTOUIC ON)
set(CMAKE_AUTORCC ON)
set(CMAKE_AUTOUIC_SEARCH_PATHS ${CMAKE_CURRENT_SOURCE_DIR}/ui)

find_package(Qt6 REQUIRED COMPONENTS Widgets)

qt_add_executable(app
  src/main.cpp
  src/mainwindow.cpp
  src/summary.cpp
  ui/mainwindow.ui
)

target_include_directories(app PRIVATE src)
target_link_libraries(app PRIVATE Qt6::Widgets)
