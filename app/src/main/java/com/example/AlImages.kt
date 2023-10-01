
import com.example.memorygame.R
import com.example.model.ImageModel

class AlImages {
    val images = ArrayList<ImageModel>()
    fun addWords(): ArrayList<ImageModel> {

        // shu yerga hamma rasmlarni qushib chiqmamiz
        // bu bizga rasmlar listini beradi
        images.add(ImageModel(R.drawable.imag_10))
        images.add(ImageModel(R.drawable.imag_11))

        return images
    }
}