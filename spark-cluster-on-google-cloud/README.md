# Spark cluster on Google Cloud



Instructions for setting up a Spark cluster on Google Cloud Dataproc.



Assuming that your Google Cloud environment is already set up:

**Step 1**	Create a [sotrage bucket](https://console.cloud.google.com/storage/browser?_ga=1.121848655.59419597.1476443859)

**Step 2**	Create a [Dataproc cluster](https://cloud.google.com/dataproc/docs/guides/create-cluster#using_the_cloud_dataproc_ui)

**Step 3**	SSH into the master node

​	     	``$ gcloud compute ssh <cluster-name>-m``



Additionally, you might want to open the Spark web UI locally. This can be done by opening YARN's UI and clicking through to your application:

1. Set up an SSH tunnel

   ``$ gcloud compute ssh  --zone=<master-host-zone> --ssh-flag="-D 8088" --ssh-flag="-N" --ssh-flag="-n" <master-host-name>``

2. Configure Googel Chrome to use proxy

   ``$ /Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --proxy-server="socks5://localhost:8088" --host-resolver-rules="MAP * 0.0.0.0 , EXCLUDE localhost" --user-data-dir=/tmp/``

3. Point your browser to port 8088 on the master

   ``http://<master-host-name>:8088``