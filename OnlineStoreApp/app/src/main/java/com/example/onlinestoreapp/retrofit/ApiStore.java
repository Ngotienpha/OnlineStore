package com.example.onlinestoreapp.retrofit;

import io.reactivex.rxjava3.core.Observable;

import com.example.onlinestoreapp.model.DonHangModel;
import com.example.onlinestoreapp.model.KhuyenMaiModel;
import com.example.onlinestoreapp.model.LoaiSanPhamModel;
import com.example.onlinestoreapp.model.MeetingModel;
import com.example.onlinestoreapp.model.MessageModel;
import com.example.onlinestoreapp.model.NewProductModel;
import com.example.onlinestoreapp.model.UserModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiStore {
    //GET
    @GET("getloaisp.php")
    Observable<LoaiSanPhamModel> getloaisp();

    @GET("khuyenmai.php")
    Observable<KhuyenMaiModel> getKhuyenMai();

    @GET("getmeeting.php")
    Observable<MeetingModel> getMeeting();
    
    @GET("getspmoi.php")
    Observable<NewProductModel> getSpmoi();

    //POST DATA
    @POST("chitiet.php")
    @FormUrlEncoded
    Observable<NewProductModel> getSanPham(
            @Field("page") int page,
            @Field("category") int category
    );

    @POST("dangki.php")
    @FormUrlEncoded
    Observable<UserModel> dangKi(
            @Field("email") String email,
            @Field("password") String password,
            @Field("username") String username,
            @Field("mobile") String mobile,
            @Field("uid") String uid
    );

    @POST("dangnhap.php")
    @FormUrlEncoded
    Observable<UserModel> dangnhap(
            @Field("email") String email,
            @Field("password") String password
    );

    @POST("reset.php")
    @FormUrlEncoded
    Observable<UserModel> resetPass(
            @Field("email") String email
    );

    @POST("donhang.php")
    @FormUrlEncoded
    Observable<MessageModel> createOrder(
            @Field("email") String email,
            @Field("sodienthoai") String sdt,
            @Field("tongtien") String tongtien,
            @Field("iduser") int id,
            @Field("diachi") String diachi,
            @Field("soluong") int soluong,
            @Field("chitiet") String chitiet
    );

    @POST("xemdonhang.php")
    @FormUrlEncoded
    Observable<DonHangModel> xemDonHang(
            @Field("iduser") int id
    );

    @POST("timkiem.php")
    @FormUrlEncoded
    Observable<NewProductModel> search(
            @Field("search") String search
    );

    @POST("updatetoken.php")
    @FormUrlEncoded
    Observable<MessageModel> updateToken(
            @Field("id") int id,
            @Field("token") String token
    );

    @POST("updatemomo.php")
    @FormUrlEncoded
    Observable<MessageModel> updateMomo(
            @Field("id") int id,
            @Field("token") String token
    );

    @POST("gettoken.php")
    @FormUrlEncoded
    Observable<UserModel> getToken(
            @Field("status") int status
    );

    @POST("deleteorder.php")
    @FormUrlEncoded
    Observable<MessageModel> deleteOrder(
            @Field("iddonhang") int id
    );

}
