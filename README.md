# Welcome to the infinite-scroll wiki!
    
     
implement RecyclerView infinite scrolling behavior in several seconds !


## copy the main files in your project
    
    

at first you need to copy [InfiniteScrollProvider.java](https://github.com/abbasAliei/infinite-scroll/blob/master/infinitescrollprovider/src/main/java/com/abbas/ali/infinitescrollprovider/InfiniteScrollProvider.java) and [OnLoadMoreListener.java](https://github.com/abbasAliei/infinite-scroll/blob/master/infinitescrollprovider/src/main/java/com/abbas/ali/infinitescrollprovider/OnLoadMoreListener.java) classes in your project which [placed here](https://github.com/abbasAliei/infinite-scroll/tree/master/infinitescrollprovider/src/main/java/com/abbas/ali/infinitescrollprovider)
   
   
   

## setup your RecyclerView
   
place codes below in your Activity which contains the RecyclerView

    InfiniteScrollProvider infiniteScrollProvider = new InfiniteScrollProvider();
    infiniteScrollProvider.setInfiniteScroll(recyclerView, new OnLoadMoreListener(){
         @Override
         public void onLoadMore() {
               // here , the recycler view is scrolled to end
               // load more data !
         }
    });


## Done !
now you are done ... !   
enjoy !!


## Author 
Abbas Aliei    

Email : info.abbasali8@gmail.com    
github : [github.com/abbasAliei](https://github.com/abbasAliei)
      
         
    
       
   
### this library is based on my dear teacher [saeed shahini](github.com/saeedSh92)'s [infinite-scroll-provider](https://github.com/saeedsh92/Infinite-Scroll-Provider) Library 
