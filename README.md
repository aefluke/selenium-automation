# selenium-automation

Malzemeler:
-Java 8
-Selenium
-TestNG
-Docker
-Selenium Grid
-Jenkins
-Github

Adımlar:
--Digitalocean'da açtığım droplet(Ubuntu)'e docker, docker-compose ve Jenkins kurdum.
--1 adet Selenium hub'a 2 adet chrome node ve 1 adet firefox node'u bağlı toplam 4 container ayakta.
--Master branchine her push yapıldığında testler crossbrowser ve paralel olarak koşuluyor.
(Droplet'in ram'i kısıtlı olduğu için her test her browser'da koşulmuyor. Ram'i artırmama rağmen yetmedi. Ama docker-compose sayesinde kolayca node sayısı sclae edilebiliyor.)
--Login senaryoları data driven olarak InvalidLoginAttemptScenarios.csv dosyasından alınarak koşuluyor. Son senaryoda hata alınıyor. 
--Hata oluştuğunda screenshot kaydediliyor.
--Test koşumu tamamlandıktan sonra oluşturulan csv dosyaları, screenshot ve test raporu(html) mail atılıyor.

--Scroll edildiğinde gelen imajların yüklenme süreleri ve response code'larını yakalayabilmek için BrowserMobProxy kütüphanesini kullandım.
Bu kütüphane browser'ın önüne proxy koyup tüm trafiği dinliyor. Fakat bu proxy'nin remote'ta çalışması için standalone proxy server'ı olarak kurup oradan dinlemek gerekiyor. Operasyonel işler planladığımdan uzun sürdüğü için bu konfigürasyona vakit ayıramadım. Dolayısıyla scroll load time  senaryosu yalnızca lokalde çalışıyor. 

--Testleri lokalde çalıştırabilmek için local isimli branchi kullanılmalıdır. Idea'da TestNG plugini kurulduktan sonra, Run > Edit Configurations.. > Add new Configuration > Test kind > Suite  seçildikten sonra Suite xml alanından proje rootundaki testNg.xml suite'i seçilir. TestNG isimli Maven profilinin test goal'u çalıştırılır.
