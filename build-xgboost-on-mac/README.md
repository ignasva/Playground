# Building XGBoost on macOS

November 2016



Setting up XGBoost on a Mac is a bit different. Here's how to do it (tested with v0.6).



- Check your *gcc* (GNU Compiler Collection) version

  ``$ gcc -v``

  If outdated, you need a recent one (6.x.x is recommended at the time of writing) so that you could take advantage of *OpenMP* and multithreading. This will take a while (~30min).

  ``$ brew install gcc --without-multilib``

  ​

- Get the source code (v0.6 in this case)

  ```
  $ git clone --recursive https://github.com/dmlc/xgboost/tree/v0.60
  $ cd xgboost
  ```

  ​

- Now we need to point XGBoost to the correct compilers, which we've just installed. In my case, *brew* installed *gcc* to */user/local/Cellar/gcc* and the version installed is 6.2.0. Add these lines to ./make/config.mk

  ```
  export CC = /usr/local/Cellar/gcc/6.2.0/bin/gcc-6
  export CXX = /usr/local/Cellar/gcc/6.2.0/bin/g++-6
  ```

  ​

- To be able to run XGBoost in distributed mode, we must set the following parameters in *./make/config.mk*

  ```
  USE_HDFS = 1
  USE_S3 = 1
  ```

  ​

- Build the shared library

  ```
  $ cp make/config.mk ./config.mk
  $ make -j4
  ```

  ​

- Build the JVM packages

  ```
  $ cd jvm-packages
  $ mvn -Dspark.version=2.0.0 package
  $ cd ../
  ```

  ​



- Installing the Python package

  ```
  $ cd python-package
  $ python setup.py install
  ```





- Installing the R package from source. In terminal:

  ``$ cd ~/; mkdir .R; cd .R; touch Makevars``

  Open *~/.R/Makevars* and paste the following lines (after adjusting the paths and the *gcc* version):

  ```
  CC=/usr/local/Cellar/gcc/6.2.0/bin/gcc-6
  CXX=/usr/local/Cellar/gcc/6.2.0/bin/g++-6
  SHLIB_CXXLD=/usr/local/Cellar/gcc/6.2.0/bin/g++-6
  FC=/usr/local/Cellar/gcc/6.2.0/bin/gfortran-6
  F77=/usr/local/Cellar/gcc/6.2.0/bin/gfortran-6
  MAKE=make -j8
  SHLIB_OPENMP_CFLAGS=-fopenmp
  SHLIB_OPENMP_CXXFLAGS=-fopenmp
  SHLIB_OPENMP_FCFLAGS=-fopenmp
  SHLIB_OPENMP_FFLAGS=-fopenmp
  ```

  Then in R:

  ```
  > install.packages("drat", repos="https://cran.rstudio.com")
  > drat:::addRepo("dmlc")
  > install.packages("xgboost", repos="http://dmlc.ml/drat/", type = "source")
  ```