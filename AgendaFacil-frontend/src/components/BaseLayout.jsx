import React from 'react';
import { Header, Sidebar, Footer } from './LayoutComponents';

const BaseLayout = ({ children }) => {
  return (
    <div className="base-layout">
      <Header />
      <div className="main-content">
        <Sidebar />
        {children}
      </div>
      <Footer />
    </div>
  );
};

export default BaseLayout;