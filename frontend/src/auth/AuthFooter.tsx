export const AuthFooter = () => {
  return (
    <footer className="bg-gray-50 mt-auto">
      <div className="max-w-7xl mx-auto px-4 py-6">
        <div className="flex flex-col md:flex-row justify-between items-center">
          <p className="text-gray-500 text-sm">
            © {new Date().getFullYear()} MyApp. All rights reserved.
          </p>
          <div className="flex space-x-4 mt-3 md:mt-0">
            <a href="#" className="text-gray-500 hover:text-indigo-600 text-sm">
              Terms
            </a>
            <a href="#" className="text-gray-500 hover:text-indigo-600 text-sm">
              Privacy
            </a>
            <a href="#" className="text-gray-500 hover:text-indigo-600 text-sm">
              Contact
            </a>
          </div>
        </div>
      </div>
    </footer>
  );
};