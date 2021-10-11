package com.example.saytahminoyunu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.core.view.isVisible
import com.example.saytahminoyunu.databinding.ActivityMainBinding
import kotlin.Int.Companion as Int1
import kotlin.Int.Companion as Int2

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var hakSayisi = 5
    var secilenSayi = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textView.isVisible= false
        binding.tahminButton.isEnabled = false
        binding.textView3.isVisible = false
        binding.textView2.text = "Kalan Hak: ${hakSayisi}"

        binding.tahminButton.setOnClickListener {
            hakSayisi -= 1
            binding.textView2.text = "Kalan Hak: ${hakSayisi}"
            if (hakSayisi == 0 ){
                binding.tahminButton.isEnabled = false
            }
            sayiTahmini()
        }
        binding.sayiTut.setOnClickListener{
            var zorluk = binding.zorlukSecenekleri.checkedRadioButtonId
            secilenSayi = when(zorluk){
                R.id.on -> (1..10).random()
                R.id.yirmi -> (1..20).random()
                else -> (1..30).random()
            }
            binding.tahminButton.isEnabled = true
            hakSayisi = 5
            binding.textView2.text = "Kalan Hak: ${hakSayisi}"
            var goster =binding.switch2.isChecked
            if(goster){
                binding.textView3.isVisible= true
                binding.textView3.text = "Belirlenen Sayı:${secilenSayi}"
            }
            var sınırsızHak = binding.switch1.isChecked
            if (sınırsızHak){
                hakSayisi = 999
                binding.textView2.text = "Kalan Hak: ${hakSayisi}"
            }
        }
    }
    fun sayiTahmini() {
        var girilenTahmin = binding.sayiGir.text.toString()
        var tahmin = girilenTahmin.toDoubleOrNull()
        binding.textView.isVisible= true
        if(tahmin == null ){
            binding.textView.text = "Tahmin Yapabilmek için önce tahminini yazmalısın"

            if (binding.switch1.isChecked){
                hakSayisi = 999
                binding.textView2.text = "Kalan Hak: ${hakSayisi}"
            } else {
                hakSayisi = 5
                binding.textView2.text = "Kalan Hak: ${hakSayisi}"
            }
            return
        }

        if (girilenTahmin == secilenSayi.toString()){
            binding.textView.text = "Tebrikler tutuğum sayıyı buldun "
            hakSayisi = 5
        } else if (hakSayisi == 0) {
            binding.textView.text = "Üzgünüm hakkın bitti seçtiğim sayı ${secilenSayi}. Tekrar oynamak için sayı belirle! "
        } else if (girilenTahmin > secilenSayi.toString()) {
            binding.textView.text = "Seçtiğim sayı, girdiğin sayının altında"
        } else if (girilenTahmin < secilenSayi.toString()){
            binding.textView.text = "Seçtiğim sayı, girdiğin sayının üstünde"
        }


    }
}