<script>
  import {url, goto} from '@sveltech/routify';
  import {user} from './dashboard/_store';
  $: if($user){
    $goto("/dashboard", {}, false);
  }
</script>
<head>
<link href="css/stylish-portfolio.min.css" rel="stylesheet">
</head>
  <a class="menu-toggle rounded" href="{$url('/login')}">
    <i class="fas fa-sign-in-alt text-white" />
  </a>
<header class="masthead d-flex">
  <div class="container text-center my-auto">
    <h1 class="mb-1">E-Tamu</h1>
    <h3 class="mb-5">
      <em>
        Membuat buku tamu dengan praktis, hemat kertas, dan ramah lingkungan
      </em>
    </h3>
    <a class="btn btn-primary btn-xl js-scroll-trigger" href="#about">
      Find Out More
    </a>
  </div>
  <div class="overlay" />
</header>
<section class="content-section bg-light" id="about">
  <div class="container text-center">
    <div class="row">
      <div class="col-lg-10 mx-auto">
        <h2>
          Ingin membuat buku tamu? Namun tak ingin mengeluarkan biaya untuk
          kertas dan printing?
        </h2>
        <p class="lead mb-5">
          E-Tamu adalah solusi yang tepat untuk anda, dengan E-tamu anda dapat
          membuat buku tamu dengan cepat dan praktis tanpa harus mengeluarkan
          biaya untuk membeli kertas atau printing!
        </p>
        <a class="btn btn-dark btn-xl js-scroll-trigger" href="#services">
          How E-Tamu Works
        </a>
      </div>
    </div>
  </div>
</section>
<section
  class="content-section bg-primary text-white text-center"
  id="services">
  <div class="container">
    <div class="content-section-heading">
      <h2 class="mb-5">How E-Tamu Works</h2>
    </div>
    <div class="row">
      <div class="col-lg-3 col-md-6 mb-5 mb-lg-0">
        <span class="service-icon rounded-circle mx-auto mb-3">
          <i class="icon-login" />
        </span>
        <h4>
          <strong>Daftar dan Login</strong>
        </h4>
        <p class="text-faded mb-0">
          Untuk Menggunakan E-tamu pengguna harus mendaftarkan diri dan login
          kedalam sistem E-Tamu
        </p>
      </div>
      <div class="col-lg-3 col-md-6 mb-5 mb-lg-0">
        <span class="service-icon rounded-circle mx-auto mb-3">
          <i class="icon-pencil" />
        </span>
        <h4>
          <strong>Buat E-Tamu Baru</strong>
        </h4>
        <p class="text-faded mb-0">
          Pengguna dapat membuat buku tamu pada dashboard sesuai dengan format
          yang pengguna inginkan
        </p>
      </div>
      <div class="col-lg-3 col-md-6 mb-5 mb-md-0">
        <span class="service-icon rounded-circle mx-auto mb-3">
          <i class="icon-link" />
        </span>
        <h4>
          <strong>Copy Link pada Browser</strong>
        </h4>
        <p class="text-faded mb-0">
          Setelah membuat buku tamu pengguna akan mendapatkan link untuk
          mengakses buku tamu, copy atau akses link tersebut pada browser
          pilihan pengguna
        </p>
      </div>
      <div class="col-lg-3 col-md-6">
        <span class="service-icon rounded-circle mx-auto mb-3">
          <i class="icon-docs" />
        </span>
        <h4>
          <strong>Download Data Tamu</strong>
        </h4>
        <p class="text-faded mb-0">
          Setelah acara selesai pengguna dapat merekap daftar hadir pada
          dashboard dan dapat didownload dalam bentuk file.
        </p>
      </div>
    </div>
  </div>
</section>
<section class="callout">
  <div class="container text-center">
    <h3 class="mx-auto mb-5">
      Segera Daftar dan Gunakan Layanan E-Tamu Untuk Acara Anda
    </h3>
      <a class="btn btn-primary btn-xl" href="{$url('/daftar')}">Daftar Sekarang</a>
  </div>
</section>
<footer class="footer text-center">
  <div class="container">
    <ul class="list-inline mb-5">
      <li class="list-inline-item">
        <a class="social-link rounded-circle text-white mr-3" href="https://www.instagram.com/khh.ini/">
          <i class="icon-social-instagram" />
        </a>
      </li>
      <li class="list-inline-item">
        <a class="social-link rounded-circle text-white mr-3" href="https://twitter.com/khh_ini">
          <i class="icon-social-twitter" />
        </a>
      </li>
      <li class="list-inline-item">
        <a class="social-link rounded-circle text-white" href="https://github.com/khh-ini">
          <i class="icon-social-github" />
        </a>
      </li>
    </ul>
    <p class="text-muted small mb-0">
      Copyright © khh.ini 2020
    </p>
  </div>
  <div />
</footer>