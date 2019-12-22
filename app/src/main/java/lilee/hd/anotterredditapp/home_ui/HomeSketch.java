//package lilee.hd.anotterredditapp.home_ui;
//
//import android.content.Intent;
//import android.net.Uri;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.Toast;
//
//import androidx.viewpager.widget.ViewPager;
//
//import com.google.android.material.tabs.TabLayout;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Objects;
//
//import lilee.hd.anotterredditapp.R;
//import lilee.hd.anotterredditapp.reddit.RedditAPI;
//import lilee.hd.anotterredditapp.model.post.Feed;
//import lilee.hd.anotterredditapp.model.token.TokenResponse;
//import lilee.hd.anotterredditapp.reddit.model.post.Children;
//import okhttp3.Credentials;
//import okhttp3.Interceptor;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import retrofit2.Call;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class HomeSketch {
//    public void onCreateStuff(){
//        //        textView = findViewById(R.id.search_result);
////        searchBtn = findViewById(R.id.search_btn);
////        mFeedName = findViewById(R.id.search_edit_text);
////        setUpViewPager();
//////        setUpBottomNav();
////        searchBtn.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                String feedName = mFeedName.getText().toString();
////                if (!feedName.equals("")) {
////                    mCurrentFeed = feedName;
////                    searchCall();
////                } else {
////                    searchCall();
////                }
////            }
////        });
//
////
////        login = findViewById(R.id.btnLogin);
////        login.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                startLogin();
////            }
////        });
//    }
//
//    private void searchCall() {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        RedditAPI redditAPI = retrofit.create(RedditAPI.class);
//        String time = "new";
//        Call<Feed> call = redditAPI.searchSubreddit(mCurrentFeed, time, sort);
//        call.enqueue(new Callback<Feed>() {
//            @Override
//            public void onResponse(Call<Feed> call, Response<Feed> response) {
//                Log.d("searchCall", "onResponse: Server Response" + response.toString());
//                Log.d("searchCall", "onResponse: received information" + response.body().toString());
//
//                ArrayList<Children> childrenArrayList = response.body().getListData().getChildren();
//                for (int i = 0; i < childrenArrayList.size(); i++) {
//
//                    Log.d("searchCall", "onResponse: \n" +
//                            "kind: " + childrenArrayList.get(i).getKind() + "\n" +
//                            "subreddit: " + childrenArrayList.get(i).getListData().getSubreddit() + "\n" +
//                            "title: " + childrenArrayList.get(i).getListData().getTitle() + "\n" +
//                            "ups: " + childrenArrayList.get(i).getListData().getUps() + "\n" +
//                            "isVideo: " + childrenArrayList.get(i).getListData().isVideo() + "\n" +
//                            "-------------------------------------------------------------------------\n\n");
//                }
//
//                if (!response.isSuccessful()) {
//                    textView.setText("Code" + response.code());
//                    return;
//                }
//                for (Children post : childrenArrayList) {
//                    content = "";
//                    content += "kind: " + post.getKind() + "\n";
//                    content += "subreddit: " + post.getListData().getSubreddit() + "\n";
//                    content += "title: " + post.getListData().getTitle() + "\n";
//                    content += "ups: " + post.getListData().getUps() + "\n";
//                    content += "isVideo: " + post.getListData().isVideo() + "\n\n";
//                    textView.append(content);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Feed> call, Throwable t) {
//                Log.e("searchCall", "onFailure: ERROR: " + t.getMessage());
//                Toast.makeText(HomeActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
//                textView.setText(t.getMessage());
//            }
//        });
//    }
//    private void redditCall() {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        RedditAPI redditAPI = retrofit.create(RedditAPI.class);
//        Call<Feed> call = redditAPI.getListData();
//        call.enqueue(new Callback<Feed>() {
//            @Override
//            public void onResponse(Call<Feed> call, Response<Feed> response) {
//                Log.d(TAG_TOKEN, "onResponse: Server Response" + response.toString());
//                Log.d(TAG_TOKEN, "onResponse: received information" + response.body().toString());
//
//                ArrayList<Children> childrenArrayList = response.body().getListData().getChildren();
//                for (int i = 0; i < childrenArrayList.size(); i++) {
//
//                    Log.d(TAG_TOKEN, "onResponse: \n" +
//                            "kind: " + childrenArrayList.get(i).getKind() + "\n" +
//                            "title: " + childrenArrayList.get(i).getListData().getTitle() + "\n" +
//                            "subscribers: " + childrenArrayList.get(i).getListData().getSubscribers() + "\n" +
//                            "public_description: " + childrenArrayList.get(i).getListData().getPublicDescription() + "\n" +
//                            "header_title: " + childrenArrayList.get(i).getListData().getHeaderTitle() + "\n" +
//                            "url: " + childrenArrayList.get(i).getListData().getUrl() + "\n" +
//                            "-------------------------------------------------------------------------\n\n");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Feed> call, Throwable t) {
//                Log.e(TAG_TOKEN, "onFailure: ERROR: " + t.getMessage());
//                Toast.makeText(HomeActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        catchAccessToken();
//
//    }
//
//    private void startLogin(){
//        Uri baseUri = Uri.parse(OAUTH_BASE_URL);
//        Uri.Builder builder = baseUri.buildUpon();
//        builder.appendQueryParameter(CLIENT_ID_KEY, CLIENT_ID);
//        builder.appendQueryParameter(RESPONSE_TYPE_KEY, RESPONSE_TYPE);
//        builder.appendQueryParameter(STATE_KEY, STATE);
//        builder.appendQueryParameter(REDIRECT_URI_KEY, REDIRECT_URI);
//        builder.appendQueryParameter(DURATION_KEY, DURATION);
//        builder.appendQueryParameter(SCOPE_KEY, SCOPE);
//        String url = builder.toString();
//
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setListData(Uri.parse(url));
//        startActivity(intent);
//        Log.d(TAG_TOKEN, "startLogin: URL: "+url);
//    }
//
//    private void catchAccessToken() {
//        final Uri uri = getIntent().getListData();
//        if (uri != null && uri.toString().startsWith(REDIRECT_URI)) {
//
//            final String code = uri.getQueryParameter("code");
//            Log.v(TAG_TOKEN, "onResume: URI received " + uri.toString());
//
//            client = new OkHttpClient.Builder();
//            client.addInterceptor(new Interceptor() {
//                @NotNull
//                @Override
//                public okhttp3.Response intercept(@NotNull Chain chain) throws IOException {
//
//                    Request request = chain.request();
//                    String credentials = Credentials.basic(CLIENT_ID, "");
//                    Request.Builder newRequest = request.newBuilder()
//                            .addHeader("Authorization", credentials);
//                    return chain.proceed(newRequest.build());
//                }
//            });
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .client(client.build())
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//            RedditAPI redditAPI = retrofit.create(RedditAPI.class);
//            Call<TokenResponse> call = redditAPI.getAccessTokenAuth("authorization_code",
//                    code,REDIRECT_URI);
//            call.enqueue(new Callback<TokenResponse>() {
//                @Override
//                public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
//                    Log.d(TAG_TOKEN, "code: " + code);
//                    Log.d(TAG_TOKEN, "onResponse: Server Response" + response.body().toString());
//                    Log.d(TAG_TOKEN, "onResponse: Server Response" + response.body().getAccessTokenAuth());
//
//
//                }
//                @Override
//                public void onFailure(Call<TokenResponse> call, Throwable t) {
//                    Log.e(TAG_TOKEN, "onFailure: ERROR: " + t.getMessage());
//                    Toast.makeText(HomeActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//        }
//
//    }
//
//    private void userFeed(){
//
//    }
//    private void setUpViewPager(){
//        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager(), 1);
//        adapter.addFragment(new DefaultFragmentChild());
//
//        adapter.addFragment(new OtterFragmentChild());
//
//        ViewPager viewPager = findViewById(R.id.container);
//        viewPager.setAdapter(adapter);
//
//        TabLayout tabLayout = findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);
//
//        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_home);
//        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_otter);
//
//    }
//
//    private void setUpBottomNav(){
//        BottomNavHelper.enableNav(mContext, navView);
//        Menu menu = navView.getMenu();
//        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
//        menuItem.setChecked(true);
//    }
//}


//    private void setUpViewPager() {
//        SectionPagerAdapter adapter = new SectionPagerAdapter(getChildFragmentManager(), 1);
//        adapter.addFragment(new DefaultFragmentChild());
//        adapter.addFragment(new OtterFragmentChild());
//
//        ViewPager viewPager = view.findViewById(R.id.viewpager_container);
//        viewPager.setAdapter(adapter);
//
//        tabLayout.setupWithViewPager(viewPager);
//
//        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_mug);
//        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_otter);
//
//    }