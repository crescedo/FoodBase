import { ref, onMounted, watch } from 'vue';
import { Camera, CameraResultType, CameraSource, Photo } from '@capacitor/camera';
import { Filesystem, Directory } from '@capacitor/filesystem';
import { Preferences } from '@capacitor/preferences';


export interface UserPhoto {
  filepath: string;
  webviewPath?: string;
} 

export function usePhotoGallery() {
   const profilPhoto = ref<UserPhoto>();

  const takePhoto = async () => {
    const photo = await Camera.getPhoto({
      resultType: CameraResultType.Uri,
      source: CameraSource.Camera,
      quality: 100,
    });
    const fileName = new Date().getTime() + '.jpeg';
    const savedFileImage = {filepath: fileName,
    webviewPath: photo.webPath,
  };
  profilPhoto.value = savedFileImage;
  };

  return {
    profilPhoto,
    takePhoto,
  }; 
}
