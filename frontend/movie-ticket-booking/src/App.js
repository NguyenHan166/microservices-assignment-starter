import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from './pages/HomePage';
import MovieDetail from './pages/MovieDetail';
import SeatSelection from './pages/SeatSelection';
import PaymentPage from './pages/PaymentPage';
import ConfirmationPage from './pages/ConfirmationPage';

const App = () => {
  return (
    <Router>
      <Routes>
        {/* Trang chính - Liệt kê các bộ phim */}
        <Route path="/" element={<HomePage />} />
        
        {/* Trang chi tiết phim */}
        <Route path="/movie/:movieId" element={<MovieDetail />} />
        
        {/* Trang chọn ghế */}
        <Route path="/seats/:movieId" element={<SeatSelection />} />
        
        {/* Trang thanh toán */}
        <Route path="/payment/:movieId" element={<PaymentPage />} />
        
        {/* Trang xác nhận vé */}
        <Route path="/confirmation" element={<ConfirmationPage />} />

        {/* Trang thanh toán */}
        <Route path="/payment/:movieId/:showtimeId" element={<PaymentPage />} />
      </Routes>
    </Router>
  );
};

export default App;
