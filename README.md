# selenium-automation

Malzemeler: </br>
-Java 8</br>
-Selenium</br>
-TestNG</br>
-Docker</br>
-Selenium Grid</br>
-Jenkins</br>
-Github</br>
</br>
Adımlar:</br>
--Digitalocean'da açtığım droplet(Ubuntu)'e docker, docker-compose ve Jenkins kurdum.</br>
--1 adet Selenium hub'a 2 adet chrome node ve 1 adet firefox node'u bağlı toplam 4 container ayakta.</br>
--Master branchine her push yapıldığında testler crossbrowser ve paralel olarak koşuluyor.</br>
(Droplet'in ram'i kısıtlı olduğu için her test her browser'da koşulmuyor. Ram'i artırmama rağmen yetmedi. Ama docker-compose sayesinde kolayca node sayısı sclae edilebiliyor.)</br>
--Login senaryoları data driven olarak InvalidLoginAttemptScenarios.csv dosyasından alınarak koşuluyor. Son senaryoda hata alınıyor. </br>
--Hata oluştuğunda screenshot kaydediliyor.</br>
--Test koşumu tamamlandıktan sonra oluşturulan csv dosyaları, screenshot ve test raporu(html) mail atılıyor.</br>
</br>
--Scroll edildiğinde gelen imajların yüklenme süreleri ve response code'larını yakalayabilmek için BrowserMobProxy kütüphanesini kullandım.</br>
Bu kütüphane browser'ın önüne proxy koyup tüm trafiği dinliyor. Fakat bu proxy'nin remote'ta çalışması için standalone proxy server kurmadım. Scroll load time  senaryosu yalnızca lokalde çalışıyor. </br>
</br>
--Testleri lokalde çalıştırabilmek için local isimli branchi kullanılmalıdır. Idea'da TestNG plugini kurulduktan sonra, Run > Edit Configurations.. > Add new Configuration > Test kind > Suite  seçildikten sonra Suite xml alanından proje rootundaki testNg.xml suite'i seçilir. TestNG isimli Maven profilinin test goal'u çalıştırılır.</br>
