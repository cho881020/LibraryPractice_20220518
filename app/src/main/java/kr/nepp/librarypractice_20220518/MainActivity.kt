package kr.nepp.librarypractice_20220518

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    fun setupEvents() {

        btnCall.setOnClickListener {

//            Intent (4) - CALL : 권한이 없어서 앱이 죽는다.

//            1. 권한 여부에 따른 행동 방침 - 인터페이스 변수

            val permissionListener = object : PermissionListener {
                override fun onPermissionGranted() {

//                    실제 CALL 액션 실행.

                    val myUri = Uri.parse("tel:01055556666")
                    val myIntent = Intent( Intent.ACTION_CALL, myUri )
                    startActivity(myIntent)

                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {

                    Toast.makeText(this@MainActivity, "권한이 없어서 통화가 불가능 합니다.", Toast.LENGTH_SHORT)
                        .show()

                }

            }

//            2. 권한 확인 요청

            TedPermission.create()
                .setPermissionListener( permissionListener )
                .setPermissions( Manifest.permission.CALL_PHONE )
                .check()

        }

        imgProfile.setOnClickListener {

            val myIntent = Intent(this, ViewPhotoActivity::class.java)
            startActivity(myIntent)

        }

    }

    fun setValues() {

        Glide.with(this).load("https://img8.yna.co.kr/photo/ap/2011/04/13/PAP20110413127601034_P4.jpg").into(imgWeb)

    }
}